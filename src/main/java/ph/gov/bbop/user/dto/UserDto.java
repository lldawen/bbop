package ph.gov.bbop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.dto.AuditableFieldsDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AuditableFieldsDto {

    private String id;
    private String password;
    private String role = "USER";
    private boolean isActive = true;
    private UserDetailDto userDetail;

}
