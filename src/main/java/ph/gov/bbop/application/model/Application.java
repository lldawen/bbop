package ph.gov.bbop.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;
import ph.gov.bbop.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public Application(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_APPLICATION_SEQ")
    @SequenceGenerator(name = "BBOP_APPLICATION_SEQ", sequenceName = "BBOP_APPLICATION_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User applicant;

    @Column(name = "APPL_TYPE", length = 10, nullable = false)
    private String appType;

    @Column(name = "PURPOSE", length = 10, nullable = false)
    private String purpose;

    @Column(name = "IS_FEE_REQUIRED", nullable = false)
    private boolean isFeeRequired;

    @Column(name = "FEE_AMOUNT", nullable = false, precision = 8, scale = 2)
    private BigDecimal feeAmount;

    @Column(name = "FEE_PAID", nullable = false, precision = 8, scale = 2)
    private BigDecimal feePaid;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    @Column(name = "PAYMENT_MODE", length = 10)
    private String paymentMode;

    @Column(name = "APPL_STATUS", length = 10, nullable = false)
    private String status;

    @Column(name = "IS_NOTIFY_VIA_EMAIL", nullable = false)
    private boolean isNotifyViaEmail;

    @Column(name = "IS_CERTIFICATE_ISSUED", nullable = false)
    private boolean isCerfificateIssued;

    @Column(name = "CERTIFICATE_ISSUE_DATE")
    private LocalDateTime certificateIssueDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ApplicationDocument> applicationDocumentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application", orphanRemoval = true, fetch = FetchType.LAZY)
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
