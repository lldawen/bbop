package ph.gov.bbop.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.service.ApplicationService;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController extends CommonRestController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll(String userId, Integer size, Integer limit) {
        if (size == null) {
            size = 0;
        }
        if (limit == null) {
            limit = 0;
        }
        return message(applicationService.findAll(userId, size, limit), Map.of("total", applicationService.getCount(userId)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return message(applicationService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody ApplicationDto applicationDto) {
        log.debug("ApplicationController | create | applicationDto: {}", applicationDto);
        return message(applicationService.create(applicationDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody ApplicationDto applicationDto) {
        log.debug("ApplicationController | update | id: {} applicationDto: {}", id ,applicationDto);
        return message(applicationService.update(id, applicationDto));
    }

    @PutMapping("/submit/{id}")
    public ResponseEntity<Message> submit(@PathVariable Long id) {
        log.debug("ApplicationController | submit | id: {}", id);
        return message(applicationService.submit(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        log.debug("ApplicationController | delete | id: {}", id);
        return message(applicationService.delete(id));
    }
}
