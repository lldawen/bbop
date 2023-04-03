package ph.gov.bbop.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private Long id;
    private String fullName;
    private String email;
    private String contactNo;
    private String address;
    private String inquiryDetails;
    private List<InquiryFeedbackDto> inquiryFeedbackList = new ArrayList<>();
}
