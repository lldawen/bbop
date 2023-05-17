package ph.gov.bbop.application.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ph.gov.bbop.application.dto.ApplicantDto;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.dto.CertificateDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.model.Certificate;
import ph.gov.bbop.code.model.Code;
import ph.gov.bbop.code.util.CodeUtil;
import ph.gov.bbop.common.CommonConstants;
import ph.gov.bbop.common.util.DateTimeUtil;
import ph.gov.bbop.user.model.User;
import ph.gov.bbop.user.model.UserDetail;
import ph.gov.bbop.user.repository.UserRepository;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApplicationMapper {

    private final UserRepository userRepository;
    private final CodeUtil codeUtil;

    private final ApplicationDocumentMapper applicationDocumentMapper;

    public ApplicationMapper(UserRepository userRepository, CodeUtil codeUtil, ApplicationDocumentMapper applicationDocumentMapper) {
        this.userRepository = userRepository;
        this.codeUtil = codeUtil;
        this.applicationDocumentMapper = applicationDocumentMapper;
    }

    public List<ApplicationDto> toDto(List<Application> applications) {
        return applications.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ApplicationDto toDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        if (application == null) {
            return applicationDto;
        }
        applicationDto.setId(application.getId());
        applicationDto.setApplicantId(application.getApplicant().getId());

        Code applTypeCode = codeUtil.getCode(CommonConstants.CC_APPL_TYPE, application.getAppType());
        applicationDto.setApplType(applTypeCode.getCode());
        applicationDto.setApplTypeDescr(applTypeCode.getCodeDescr());

        Code purposeCode = codeUtil.getCode(getPurposeCategory(applicationDto.getApplType()), application.getPurpose());
        applicationDto.setPurpose(purposeCode.getCode());
        applicationDto.setPurposeDescr(purposeCode.getCodeDescr());

        applicationDto.setFeeRequired(application.isFeeRequired());
        applicationDto.setFeeAmount(application.getFeeAmount());
        applicationDto.setFeePaid(application.getFeePaid());
        applicationDto.setPaymentDate(DateTimeUtil.format(application.getPaymentDate()));
        if (application.getPaymentMode() != null) {
            Code paymentModeCode = codeUtil.getCode(CommonConstants.CC_PAYMENT_MODE, application.getPaymentMode());
            applicationDto.setPaymentMode(paymentModeCode.getCode());
            applicationDto.setPaymentModeDescr(paymentModeCode.getCodeDescr());
        }
        Code statusCode = codeUtil.getCode(CommonConstants.CC_APPL_STATUS, application.getStatus());
        applicationDto.setStatus(statusCode.getCode());
        applicationDto.setStatusDescr(statusCode.getCodeDescr());
        applicationDto.setNotifyViaEmail(application.isNotifyViaEmail());
        applicationDto.setCertificateIssued(application.isCerfificateIssued());
        applicationDto.setCertificateIssueDate(DateTimeUtil.formatWithTime(application.getCertificateIssueDate()));
        applicationDto.setApplDocList(applicationDocumentMapper.toDto(application.getApplicationDocumentList()));
        applicationDto.setCertificateList(toCertificateDto(application.getCertificateList()));
        return applicationDto;
    }

    public List<CertificateDto> toCertificateDto(List<Certificate> certificates) {
        return certificates.stream().map(this::toCertificateDto).collect(Collectors.toList());
    }

    public CertificateDto toCertificateDto(Certificate certificate) {
        CertificateDto certificateDto = new CertificateDto();
        if (certificate == null) {
            return certificateDto;
        }
        certificateDto.setId(certificate.getId());
        certificateDto.setApplId(certificate.getApplication().getId());
        certificateDto.setGeneratedFilename(certificate.getGeneratedFilename());
        return certificateDto;
    }

    public Application toEntity(ApplicationDto applicationDto) {
        return toEntity(applicationDto, null);
    }

    public Application toEntity(ApplicationDto applicationDto, Application application) {
        if (application == null) {
            application = new Application();
        }
        application.setApplicant(userRepository.findById(applicationDto.getApplicantId()).orElseThrow());
        application.setAppType(codeUtil.getCode(CommonConstants.CC_APPL_TYPE, applicationDto.getApplType()).getCode());
        application.setPurpose(codeUtil.getCode(getPurposeCategory(applicationDto.getApplType()), applicationDto.getPurpose()).getCode());
        application.setFeeRequired(applicationDto.isFeeRequired());
        application.setFeeAmount(applicationDto.getFeeAmount());
        application.setFeePaid(applicationDto.getFeePaid());
        application.setPaymentDate(DateTimeUtil.parse(applicationDto.getPaymentDate()));
        Code paymentModeCode = codeUtil.getCode(CommonConstants.CC_PAYMENT_MODE, applicationDto.getPaymentMode());
        if (paymentModeCode != null) {
            application.setPaymentMode(paymentModeCode.getCode());
        }
        application.setStatus(codeUtil.getCode(CommonConstants.CC_APPL_STATUS, applicationDto.getStatus()).getCode());
        application.setNotifyViaEmail(applicationDto.isNotifyViaEmail());
        application.setCerfificateIssued(applicationDto.isCertificateIssued());
        application.setCertificateIssueDate(DateTimeUtil.parseWithTime(applicationDto.getCertificateIssueDate()));
        return application;
    }

    private String getPurposeCategory(String applType) {
        if (CommonConstants.APPL_TYPE_CLEARANCE.equals(applType)) {
            return CommonConstants.CC_CLEARANCE_PURPOSE;
        }
        if (CommonConstants.APPL_TYPE_INDIGENCY.equals(applType)) {
            return CommonConstants.CC_INDIGENCY_PURPOSE;
        }
        if (CommonConstants.APPL_TYPE_RESIDENCY.equals(applType)) {
            return CommonConstants.CC_RESIDENCY_PURPOSE;
        }
        return null;
    }

    public ApplicantDto toApplicantDto(User applicant) {
        ApplicantDto applicantDto = new ApplicantDto();
        UserDetail applDtl = applicant.getUserDetail();
        StringJoiner nameJoiner = new StringJoiner(" ");
        nameJoiner.add(applDtl.getFirstName())
                .add(applDtl.getMiddleName())
                .add(applDtl.getLastName());
        applicantDto.setFullName(nameJoiner.toString());
        applicantDto.setAge(String.valueOf(DateTimeUtil.getAge(applDtl.getBirthDate())));
        applicantDto.setContactNo(applDtl.getContactNo1());
        StringJoiner addrJoiner = new StringJoiner(" ");
        addrJoiner.add(applDtl.getHouseBlkNo())
                .add(codeUtil.getDescription(CommonConstants.CC_DISTRICT, applDtl.getDistrict()))
                .add(codeUtil.getDescription(CommonConstants.CC_STREET, applDtl.getStreet()))
                        .add("Street");
        applicantDto.setAddress(addrJoiner.toString());
        return applicantDto;
    }
}
