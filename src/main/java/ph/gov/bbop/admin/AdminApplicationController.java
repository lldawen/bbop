package ph.gov.bbop.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.service.ApplicationService;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;
import ph.gov.bbop.common.util.FileUtil;

import java.io.File;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/application")
public class AdminApplicationController extends CommonRestController {

    private final ApplicationService applicationService;

    public AdminApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll(Integer size, Integer limit) {
        if (size == null) {
            size = 0;
        }
        if (limit == null) {
            limit = 0;
        }
        return message(applicationService.findAllSubmittedApplications(size, limit), Map.of("total", applicationService.countSubmittedApplications()));
    }

    @PutMapping("/savePayment/{id}")
    public ResponseEntity<Message> savePayment(@PathVariable Long id, @RequestBody ApplicationDto applicationDto) {
        log.debug("ApplicationController | savePayment | id: {}", id);
        return message(applicationService.savePayment(id, applicationDto));
    }
    @PutMapping("/approve/{id}")
    public ResponseEntity<Message> approve(@PathVariable Long id) {
        log.debug("ApplicationController | approve | id: {}", id);
        return message(applicationService.approve(id));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<Message> reject(@PathVariable Long id) {
        log.debug("ApplicationController | reject | id: {}", id);
        return message(applicationService.reject(id));
    }

    @PutMapping("/conveyAndClose/{id}")
    public ResponseEntity<Message> conveyAndClose(@PathVariable Long id) {
        log.debug("ApplicationController | conveyAndClose | id: {}", id);
        return message(applicationService.conveyAndClose(id));
    }

    @GetMapping("/document/download/{id}")
    public void download(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        log.debug("ApplicationController | download | id: {}", id);
        String documentPath = applicationService.getCertificateDocumentPath(id);
        File file = new File(FileUtil.decodeDocumentPath(documentPath));
        if (file.exists()) {
            FileUtil.downloadFile(request, response, file);
        }
    }
}
