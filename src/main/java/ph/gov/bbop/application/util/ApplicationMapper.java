package ph.gov.bbop.application.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.dto.CertificateDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.model.Certificate;
import ph.gov.bbop.code.util.CodeUtil;
import ph.gov.bbop.common.CommonConstants;
import ph.gov.bbop.common.util.DateTimeUtil;
import ph.gov.bbop.user.repository.UserRepository;

import java.util.List;
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
        applicationDto.setApplType(application.getAppType().getCode());
        applicationDto.setApplTypeDescr(application.getAppType().getCodeDescr());
        applicationDto.setPurpose(application.getPurpose().getCode());
        applicationDto.setPurposeDescr(application.getPurpose().getCodeDescr());
        applicationDto.setFeeRequired(application.isFeeRequired());
        applicationDto.setFeeAmount(application.getFeeAmount());
        applicationDto.setFeePaid(application.getFeePaid());
        applicationDto.setPaymentDate(DateTimeUtil.formatWithTime(application.getPaymentDate()));
        if (application.getPaymentMode() != null) {
            applicationDto.setPaymentMode(application.getPaymentMode().getCode());
            applicationDto.setPaymentModeDescr(application.getPaymentMode().getCodeDescr());
        }
        applicationDto.setStatus(application.getStatus().getCode());
        applicationDto.setStatusDescr(application.getStatus().getCodeDescr());
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
        application.setAppType(codeUtil.getCode(CommonConstants.CC_APPL_TYPE, applicationDto.getApplType()));
        application.setPurpose(codeUtil.getCode(CommonConstants.CC_PURPOSE, applicationDto.getPurpose()));
        application.setFeeRequired(applicationDto.isFeeRequired());
        application.setFeeAmount(applicationDto.getFeeAmount());
        application.setFeePaid(applicationDto.getFeePaid());
        application.setPaymentDate(DateTimeUtil.parseWithTime(applicationDto.getPaymentDate()));
        application.setPaymentMode(codeUtil.getCode(CommonConstants.CC_PAYMENT_MODE, applicationDto.getPaymentMode()));
        application.setStatus(codeUtil.getCode(CommonConstants.CC_STATUS, applicationDto.getStatus()));
        application.setNotifyViaEmail(applicationDto.isNotifyViaEmail());
        application.setCerfificateIssued(applicationDto.isCertificateIssued());
        application.setCertificateIssueDate(DateTimeUtil.parseWithTime(applicationDto.getCertificateIssueDate()));
        return application;
    }
}
