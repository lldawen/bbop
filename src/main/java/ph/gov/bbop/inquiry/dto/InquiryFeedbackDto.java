package ph.gov.bbop.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryFeedbackDto {
    private Long id;
    private Long inquiryId;
    private String respondentUserId;
    private String feedbackDetails;
    private boolean isSentViaEmail;
}
