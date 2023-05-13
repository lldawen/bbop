package ph.gov.bbop.application.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.application.dto.ApplicationDocumentDto;
import ph.gov.bbop.application.service.ApplicationDocumentService;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;
import ph.gov.bbop.common.util.FileUtil;

import java.io.File;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/application/document")
public class ApplicationDocumentController extends CommonRestController {

    private final ApplicationDocumentService applicationDocumentService;

    public ApplicationDocumentController(ApplicationDocumentService applicationDocumentService) {
        this.applicationDocumentService = applicationDocumentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll(Long applId, Integer size, Integer limit) {
        if (size == null) {
            size = 0;
        }
        if (limit == null) {
            limit = 0;
        }
        return message(applicationDocumentService.findAll(applId, size, limit), Map.of("total", applicationDocumentService.countByApplication(applId)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return message(applicationDocumentService.findById(id));
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Message> create(@ModelAttribute ApplicationDocumentDto applicationDocumentDto) {
        log.debug("ApplicationDocumentController | create | applicationDocumentDto: {}", applicationDocumentDto);
        return message(applicationDocumentService.create(applicationDocumentDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        log.debug("ApplicationDocumentController | delete | id: {}", id);
        return message(applicationDocumentService.delete(id));
    }

    @GetMapping("/download/{documentPath}")
    public void downloadDocument(@PathVariable String documentPath, HttpServletRequest request, HttpServletResponse response) {
        File file = new File(FileUtil.decodeDocumentPath(documentPath));
        if (file.exists()) {
            FileUtil.downloadFile(request, response, file);
        }
    }
}
