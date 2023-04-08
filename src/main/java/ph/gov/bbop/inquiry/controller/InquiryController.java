package ph.gov.bbop.inquiry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.Message;
import ph.gov.bbop.inquiry.dto.InquiryDto;
import ph.gov.bbop.inquiry.dto.InquiryFeedbackDto;
import ph.gov.bbop.inquiry.service.InquiryService;

@RestController
@RequestMapping("/api/v1/inquiry")
public class InquiryController extends CommonRestController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return message(inquiryService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Message> findById(@PathVariable  Long id) {
        return message(inquiryService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody InquiryDto inquiryDto) {
        return message(inquiryService.create(inquiryDto));
    }

    @PostMapping("/get/{id}/feedback/create")
    public ResponseEntity<Message> createFeedback(@PathVariable Long id, @RequestBody InquiryFeedbackDto inquiryFeedbackDto) {
        return message((inquiryService.createFeedback(id, inquiryFeedbackDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody InquiryDto inquiryDto) {
        return message(inquiryService.update(id, inquiryDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        return message(inquiryService.delete(id));
    }
}
