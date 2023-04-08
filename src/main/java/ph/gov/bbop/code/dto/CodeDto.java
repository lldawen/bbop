package ph.gov.bbop.code.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDto {

    private Long id;
    private String category;
    private String categoryDescription;
    private String code;
    private String codeDescription;
    private boolean isActive;

}
