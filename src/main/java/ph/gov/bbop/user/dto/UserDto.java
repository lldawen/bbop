package ph.gov.bbop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String password;
    private boolean isActive;
    private boolean isAdmin;
    private UserDetailDto userDetail;
}
