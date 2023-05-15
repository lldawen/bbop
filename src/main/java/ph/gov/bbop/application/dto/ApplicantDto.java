package ph.gov.bbop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDto {
    private String fullName;
    private String age;
    private String contactNo;
    private String address;
}
