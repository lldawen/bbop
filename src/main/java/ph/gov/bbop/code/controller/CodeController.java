package ph.gov.bbop.code.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.code.dto.CodeDto;
import ph.gov.bbop.code.service.CodeService;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;

@RestController
@RequestMapping("/api/v1/code")
public class CodeController extends CommonRestController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return message(codeService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return message((codeService.findById(id)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Message> findByCategory(@PathVariable String category) {
        return message((codeService.findByCategory(category)));
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody CodeDto codeDto) {
        return message(codeService.create(codeDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody CodeDto codeDto) {
        return message(codeService.update(id, codeDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return message(codeService.delete(id));
    }
}
