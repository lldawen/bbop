package ph.gov.bbop.inquiry.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BBOP_INQUIRY")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Inquiry extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BBOP_INQUIRY_SEQ")
    @SequenceGenerator(name = "BBOP_INQUIRY_SEQ", sequenceName = "BBOP_INQUIRY_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FULL_NAME", length = 255, nullable = false)
    private String fullName;

    @Column(length = 75, nullable = false)
    private String email;

    @Column(name = "CONTACT_NO", length = 20, nullable = false)
    private String contactNo;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(name = "INQUIRY_DETAIL", length = 4000, nullable = false)
    private String inquiryDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inquiry", orphanRemoval = true)
    private List<InquiryFeedback> inquiryFeedbackList = new ArrayList<>();

    public void addFeedback(InquiryFeedback inquiryFeedback) {
        if (inquiryFeedback == null) {
            throw new RuntimeException("InquiryFeedback must not be null.");
        }
        this.inquiryFeedbackList.add(inquiryFeedback);
    }
}
