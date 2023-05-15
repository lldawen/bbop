package ph.gov.bbop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private Long id;
    private String applicantId;
    private String applType;
    private String applTypeDescr;
    private String purpose;
    private String purposeDescr;
    private boolean isFeeRequired;
    private BigDecimal feeAmount;
    private BigDecimal feePaid;
    private String paymentDate;
    private String paymentMode;
    private String paymentModeDescr;
    private String status;
    private String statusDescr;
    private boolean isNotifyViaEmail;
    private boolean isCertificateIssued;
    private String certificateIssueDate;
    private ApplicantDto applicant;
    private List<ApplicationDocumentDto> applDocList = new ArrayList<>();
    private List<CertificateDto> certificateList = new ArrayList<>();
}
