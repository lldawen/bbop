package ph.gov.bbop.inquiry.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;
import ph.gov.bbop.user.model.User;

@Entity
@Table(name = "BBOP_INQUIRY_FEEDBACK")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InquiryFeedback extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_INQUIRY_FB_SEQ")
    @SequenceGenerator(name = "BBOP_INQUIRY_FB_SEQ", sequenceName = "BBOP_INQUIRY_FB_SEQ", allocationSize = 1, initialValue = 1000)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "INQUIRY_ID")
    private Inquiry inquiry;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "RESPONDENT_USER_ID")
    private User respondentUser;

    @Column(name = "FEEDBACK_DETAILS", length = 4000, nullable = false)
    private String feedbackDetails;

    @Column(name = "IS_SENT_VIA_EMAIL", nullable = false)
    private boolean isSentViaEmail;
}
