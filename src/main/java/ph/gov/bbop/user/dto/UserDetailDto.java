package ph.gov.bbop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.gov.bbop.common.dto.AuditableFieldsDto;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailDto extends AuditableFieldsDto {

    //personal information
    private String userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String birthDate;
    private Integer age;
    private String civilStatus;
    private String contactNo1;
    private String contactNo2;
    private String email;
    private Blob signature;

    //address
    private String houseBlkNo;
    private String district;
    private String street;

}
