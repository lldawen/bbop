package ph.gov.bbop.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.code.model.Code;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User applicant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "APPLICATION_TYPE", nullable = false)
    private Code appType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PURPOSE", nullable = false)
    private Code purpose;

    @Column(name = "IS_FEE_REQUIRED", nullable = false)
    private boolean isFeeRequired;

    @Column(name = "FEE_AMOUNT", nullable = false, precision = 8, scale = 2)
    private BigDecimal feeAmount;

    @Column(name = "FEE_PAID", nullable = false, precision = 8, scale = 2)
    private BigDecimal feePaid;

    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "PAYMENT_MODE", nullable = true)
    private Code paymentMode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "STATUS", nullable = false)
    private Code status;

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
