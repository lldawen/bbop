package ph.gov.bbop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetail {
    private boolean success;
    private String message;
    private Object data;
}
