package ph.gov.bbop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResultDto {
    private String id;
    private String fullName;
    private String role;
    private String isActive;
}
