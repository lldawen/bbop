package ph.gov.bbop.inquiry.service;

import org.springframework.stereotype.Service;
import ph.gov.bbop.inquiry.dto.InquiryDto;
import ph.gov.bbop.inquiry.dto.InquiryFeedbackDto;
import ph.gov.bbop.inquiry.model.Inquiry;
import ph.gov.bbop.inquiry.repository.InquiryRepository;
import ph.gov.bbop.inquiry.util.InquiryMapper;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryMapper inquiryMapper;

    public InquiryService(InquiryRepository inquiryRepository, InquiryMapper inquiryMapper) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryMapper = inquiryMapper;
    }

    public List<InquiryDto> findAll() {
        return inquiryMapper.toDto(inquiryRepository.findAll());
    }

    public InquiryDto findById(Long id) {
        return inquiryMapper.toDto(inquiryRepository.findById(id).orElse(null));
    }

    public InquiryDto create(InquiryDto inquiryDto) {
        if (inquiryDto.getId() != null) {
            throw new RuntimeException("Inquiry already exists.");
        }
        return save(inquiryDto);
    }

    public InquiryFeedbackDto createFeedback(Long id, InquiryFeedbackDto inquiryFeedbackDto) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(RuntimeException::new);
        inquiry.addFeedback(inquiryMapper.toEntity(inquiryFeedbackDto, inquiry));
        inquiryRepository.save(inquiry);
        return inquiryMapper.toDto(inquiry.getInquiryFeedbackList().get(inquiry.getInquiryFeedbackList().size() - 1));
    }

    public InquiryDto update(Long id, InquiryDto inquiryDto) {
        if (!inquiryRepository.existsById(id)) {
            throw new RuntimeException("Inquiry does not exist.");
        }
        return save(inquiryDto);
    }

    private InquiryDto save(InquiryDto inquiryDto) {
        return inquiryMapper.toDto(inquiryRepository.save(inquiryMapper.toEntity(inquiryDto)));
    }

    public InquiryDto delete(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(RuntimeException::new);
        InquiryDto inquiryDto = inquiryMapper.toDto(inquiry);
        inquiryRepository.deleteById(id);
        return inquiryDto;
    }
}
