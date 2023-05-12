package ph.gov.bbop.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDocumentDto {
    private Long id;
    private Long applId;
    private String documentType;
    private String documentTypeDescr;
    private String documentName;
    private MultipartFile documentFile;
    private String documentPath;
}
