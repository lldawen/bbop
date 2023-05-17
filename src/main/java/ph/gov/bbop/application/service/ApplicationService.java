package ph.gov.bbop.application.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ph.gov.bbop.application.dto.ApplicantDto;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.model.Certificate;
import ph.gov.bbop.application.repository.ApplicationRepository;
import ph.gov.bbop.application.util.ApplicationMapper;
import ph.gov.bbop.code.model.Code;
import ph.gov.bbop.code.util.CodeUtil;
import ph.gov.bbop.common.CommonConstants;
import ph.gov.bbop.common.util.DateTimeUtil;
import ph.gov.bbop.common.util.DocumentGenerator;
import ph.gov.bbop.common.util.EmailSenderAPI;
import ph.gov.bbop.common.util.FileUtil;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.model.UserDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Service
@Slf4j
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final CodeUtil codeUtil;
    private final DocumentGenerator documentGenerator;
    private EmailSenderAPI emailSenderAPI;


    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, CodeUtil codeUtil, DocumentGenerator documentGenerator, EmailSenderAPI emailSenderAPI) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.codeUtil = codeUtil;
        this.documentGenerator = documentGenerator;
        this.emailSenderAPI = emailSenderAPI;
    }

    public List<ApplicationDto> findAll() {
        return applicationMapper.toDto(applicationRepository.findAll());
    }

    public List<ApplicationDto> findAllSubmittedApplications(int size, int limit) {
        Page<Application> pagedApplications = applicationRepository.findByStatusNotOrderByCreatedDateDesc(CommonConstants.APPL_STATUS_DRAFT, PageRequest.of(size, limit));
        return applicationMapper.toDto(pagedApplications.getContent());
    }

    public long countSubmittedApplications() {
        return applicationRepository.countByStatusNot(CommonConstants.APPL_STATUS_DRAFT);
    }

    public List<ApplicationDto> findAll(String userId, int size, int limit) {
        Page<Application> pagedApplications = applicationRepository.findByApplicantAndStatusNotOrderByCreatedDateDesc(new User(userId), CommonConstants.APPL_STATUS_DELETED, PageRequest.of(size, limit));
        return applicationMapper.toDto(pagedApplications.getContent());
    }

    public long getCount(String userId) {
        return applicationRepository.countByApplicant(new User(userId));
    }

    public ApplicationDto findById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        ApplicationDto applicationDto = applicationMapper.toDto(application);
        applicationDto.setApplicant(applicationMapper.toApplicantDto(application.getApplicant()));
        return applicationDto;
    }

    public ApplicationDto create(ApplicationDto applicationDto) {
        if (applicationDto.getId() != null && applicationRepository.existsById(applicationDto.getId())) {
            throw new RuntimeException("Application already exists.");
        }
        applicationDto.setStatus(CommonConstants.APPL_STATUS_DRAFT);
        Application application = applicationRepository.save(applicationMapper.toEntity(applicationDto));
        return applicationMapper.toDto(application);
    }

    public ApplicationDto update(Long id, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(id).orElseThrow();
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public ApplicationDto submit(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        Code code = codeUtil.getCode(CommonConstants.CC_APPL_STATUS, CommonConstants.APPL_STATUS_OPEN);
        application.setStatus(code.getCode());
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public ApplicationDto delete(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_DELETED);
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto withdraw(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_WITHDRAWN);
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto savePayment(Long id, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setPaymentMode(applicationDto.getPaymentMode());
        application.setFeePaid(applicationDto.getFeePaid());
        application.setPaymentDate(DateTimeUtil.parse(applicationDto.getPaymentDate()));
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public ApplicationDto approve(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_APPROVED);

        String docFilePath = documentGenerator.generate(application.getAppType(), getDocumentParameters(application));
        Certificate certificate = new Certificate();
        certificate.setApplication(application);
        certificate.setGeneratedFilename(FilenameUtils.getName(docFilePath));
        certificate.setGeneratedFilePath(FileUtil.encodeDocumentPath(docFilePath));
        application.addCertificate(certificate);

        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto reject(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_REJECTED);
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto conveyAndClose(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_CLOSED);
        application.setCerfificateIssued(true);
        application.setCertificateIssueDate(LocalDateTime.now());
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public void emailEmailToApplicant(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        emailSenderAPI.send(getEmailParameters(application));
    }

    public String getCertificateDocumentPath(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        if (application.getCertificateList().isEmpty()) {
            throw new RuntimeException("Certificate is not yet generated.");
        }
        Certificate certificate = application.getCertificateList().get(0);
        log.debug("retrieved certificate file path: {}", certificate.getGeneratedFilePath());
        return FileUtil.decodeDocumentPath(certificate.getGeneratedFilePath());
    }

    /**
     * brute force mapping of values for each application type, this should be configurable
     */
    private Map<String, String> getDocumentParameters(Application application) {
        Map<String, String> docParameterMap = new HashMap<>();
//        if (CommonConstants.APPL_TYPE_CLEARANCE.equals(application.getAppType())) {
            UserDetail applicant = application.getApplicant().getUserDetail();
            docParameterMap.put("APPL_ID", application.getId().toString());
            docParameterMap.put("${FULL_NAME}", getApplicantFullName(applicant));
            docParameterMap.put("${AGE}", String.valueOf(getApplicantAge(applicant.getBirthDate())));
            docParameterMap.put("${CIVIL_STATUS}", codeUtil.getDescription(CommonConstants.CC_CIVIL_STATUS, applicant.getCivilStatus()));
            docParameterMap.put("${ADDRESS}", getApplicantAddress(applicant));
            docParameterMap.put("${ISSUE_DATE}", DateTimeUtil.format(LocalDate.now(), "dd MMMM YYYY"));
            docParameterMap.put("${SIGNATURE}", getApplicantFullName(applicant).toUpperCase());
//        }
        return docParameterMap;
    }

    private String getApplicantFullName(UserDetail applicant) {
        StringJoiner nameJoiner = new StringJoiner(" ");
        nameJoiner.add(applicant.getFirstName());
        nameJoiner.add(applicant.getMiddleName().substring(0, 1) + ".");
        nameJoiner.add(applicant.getLastName());
        return nameJoiner.toString();

    }

    private int getApplicantAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private String getApplicantAddress(UserDetail applicant) {
        StringJoiner addressJoiner = new StringJoiner(", ");
        addressJoiner.add(applicant.getHouseBlkNo());
        addressJoiner.add(codeUtil.getDescription(CommonConstants.CC_DISTRICT, applicant.getDistrict()));
        addressJoiner.add(codeUtil.getDescription(CommonConstants.CC_STREET, applicant.getStreet()) + " Street");
        return addressJoiner.toString();
    }

    private Map<String, String> getEmailParameters(Application application) {
        Map<String, String> parameters = new HashMap<>();
        String applType = codeUtil.getDescription(CommonConstants.CC_APPL_TYPE, application.getAppType());
        parameters.put("toEmail", application.getApplicant().getUserDetail().getEmail());
        parameters.put("subject", "Application for " + applType);
        parameters.put("body", "Thank you for your application! Please refer to the attached file for your " + applType + ".");
        Certificate certificate = application.getCertificateList().stream().max(Comparator.comparing(Certificate::getId)).orElseThrow();
        parameters.put("certificate", FileUtil.decodeDocumentPath(certificate.getGeneratedFilePath()));
        return parameters;
    }
}
