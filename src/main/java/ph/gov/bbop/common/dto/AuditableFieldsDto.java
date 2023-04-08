package ph.gov.bbop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditableFieldsDto implements Serializable {

    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;

}
