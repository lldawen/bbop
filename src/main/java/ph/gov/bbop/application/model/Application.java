package ph.gov.bbop.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;
import ph.gov.bbop.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BBOP_APPLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Application extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_APPLICATION_SEQ")
    @SequenceGenerator(name = "BBOP_APPLICATION_SEQ", sequenceName = "BBOP_APPLICATION_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "USER_ID")
    private User applicant;

    @Column(name = "APPLICATION_TYPE", nullable = false)
    private ApplicationType applicationType;

    @Column(name = "PURPOSE", length = 3, nullable = false)
    private String purpose;

    @Column(name = "IS_FEE_REQUIRED", nullable = false)
    private boolean isFeeRequired;

    @Column(name = "FEE_AMOUNT", nullable = false, precision = 8, scale = 2)
    private BigDecimal feeAmount;

    @Column(name = "FEE_PAID", nullable = false, precision = 8, scale = 2)
    private BigDecimal feePaid;

    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    @Column(name = "PAYMENT_MODE", length = 3, nullable = false)
    private String paymentMode;

    @Column(name = "STATUS", length = 3, nullable = false)
    private String status;

    @Column(name = "IS_NOTIFY_VIA_EMAIL", nullable = false)
    private boolean isNotifyViaEmail;

    @Column(name = "IS_CERTIFICATE_ISSUED", nullable = false)
    private boolean isCerfificateIssued;

    @Column(name = "CERTIFICATE_ISSUE_DATE")
    private LocalDateTime certificateIssueDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true)
    private List<ApplicationDocument> applicationDocumentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true)
    private List<Certificate> certificateList = new ArrayList<>();

    public void addApplicationDocument(ApplicationDocument applicationDocument) {
        if (applicationDocument == null) {
            throw new IllegalArgumentException("ApplicationDocument must not be null.");
        }
        this.applicationDocumentList.add(applicationDocument);
    }

    public void addCertificate(Certificate certificate) {
        if (certificate == null) {
            throw new IllegalArgumentException("Certificate must not be null");
        }
        this.certificateList.add(certificate);
    }
}
