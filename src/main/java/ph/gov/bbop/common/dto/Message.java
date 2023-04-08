package ph.gov.bbop.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    protected boolean success = true;
    protected String message = "Success";
}
