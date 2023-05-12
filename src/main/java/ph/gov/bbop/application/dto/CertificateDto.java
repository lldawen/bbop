package ph.gov.bbop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDto {
    private Long id;
    private Long applId;
    private String generatedFilename;
}
