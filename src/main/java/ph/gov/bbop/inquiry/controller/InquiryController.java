package ph.gov.bbop.inquiry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.bbop.common.controller.CommonRestController;
import ph.gov.bbop.common.dto.KeyValue;
import ph.gov.bbop.common.dto.MessageDetail;
import ph.gov.bbop.inquiry.dto.InquiryDto;
import ph.gov.bbop.inquiry.dto.InquiryFeedbackDto;
import ph.gov.bbop.inquiry.service.InquiryService;

@RestController
@RequestMapping("/v1/inquiry")
public class InquiryController extends CommonRestController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/all")
    public ResponseEntity<MessageDetail> findAll() {
        return responseEntityWithDetails(inquiryService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MessageDetail> findById(@PathVariable  Long id) {
        return responseEntityWithDetails(inquiryService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDetail> create(@RequestBody InquiryDto inquiryDto) {
        return responseEntityWithDetails(inquiryService.create(inquiryDto));
    }

    @PostMapping("/get/{id}/feedback/create")
    public ResponseEntity<MessageDetail> createFeedback(@PathVariable Long id, @RequestBody InquiryFeedbackDto inquiryFeedbackDto) {
        return responseEntityWithDetails((inquiryService.createFeedback(id, inquiryFeedbackDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageDetail> update(@PathVariable Long id, @RequestBody InquiryDto inquiryDto) {
        return responseEntityWithDetails(inquiryService.update(id, inquiryDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDetail> delete(@PathVariable Long id) {
        return responseEntityWithDetails(inquiryService.delete(id));
    }
}
