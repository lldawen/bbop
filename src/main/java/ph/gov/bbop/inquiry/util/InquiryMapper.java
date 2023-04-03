package ph.gov.bbop.inquiry.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.inquiry.dto.InquiryDto;
import ph.gov.bbop.inquiry.dto.InquiryFeedbackDto;
import ph.gov.bbop.inquiry.model.Inquiry;
import ph.gov.bbop.inquiry.model.InquiryFeedback;
import ph.gov.bbop.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InquiryMapper {

    private final UserRepository userRepository;

    public InquiryMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<InquiryDto> toDto(List<Inquiry> inquiryList) {
        return inquiryList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public InquiryDto toDto(Inquiry inquiry) {
        InquiryDto inquiryDto = new InquiryDto();
        if (inquiry == null) {
            return inquiryDto;
        }
        inquiryDto.setId(inquiry.getId());
        inquiryDto.setFullName(inquiry.getFullName());
        inquiryDto.setEmail(inquiry.getEmail());
        inquiryDto.setContactNo(inquiry.getContactNo());
        inquiryDto.setAddress(inquiry.getAddress());
        inquiryDto.setInquiryDetails(inquiry.getInquiryDetails());
        inquiryDto.setInquiryFeedbackList(inquiry.getInquiryFeedbackList()
                .stream().map(this::toDto).collect(Collectors.toList()));
        return inquiryDto;
    }

    public InquiryFeedbackDto toDto(InquiryFeedback inquiryFeedback) {
        InquiryFeedbackDto inquiryFeedbackDto = new InquiryFeedbackDto();
        if (inquiryFeedback == null) {
            return inquiryFeedbackDto;
        }
        inquiryFeedbackDto.setId(inquiryFeedback.getId());
        inquiryFeedbackDto.setInquiryId(inquiryFeedback.getInquiry().getId());
        inquiryFeedbackDto.setRespondentUserId(inquiryFeedback.getRespondentUser().getId());
        inquiryFeedbackDto.setFeedbackDetails(inquiryFeedback.getFeedbackDetails());
        inquiryFeedbackDto.setSentViaEmail(inquiryFeedback.isSentViaEmail());
        return inquiryFeedbackDto;
    }

    public Inquiry toEntity(InquiryDto inquiryDto) {
        if (inquiryDto == null) {
            throw new RuntimeException("InquiryDto must not be null.");
        }
        Inquiry inquiry = new Inquiry();
        inquiry.setId(inquiryDto.getId());
        inquiry.setFullName(inquiryDto.getFullName());
        inquiry.setEmail(inquiryDto.getEmail());
        inquiry.setContactNo(inquiryDto.getContactNo());
        inquiry.setAddress(inquiryDto.getAddress());
        inquiry.setInquiryDetails(inquiryDto.getInquiryDetails());
        inquiry.setInquiryFeedbackList(inquiryDto.getInquiryFeedbackList()
                .stream().map(feedback -> this.toEntity(feedback, inquiry)).collect(Collectors.toList()));
        inquiry.setCreatedBy("dawen"); //TODO
        inquiry.setCreatedDate(LocalDateTime.now());
        return inquiry;
    }

    public InquiryFeedback toEntity(InquiryFeedbackDto inquiryFeedbackDto, Inquiry inquiry) {
        if (inquiryFeedbackDto == null) {
            throw new RuntimeException("InquiryFeedback must not be null.");
        }
        InquiryFeedback inquiryFeedback = new InquiryFeedback();
        inquiryFeedback.setId(inquiryFeedbackDto.getId());
        inquiryFeedback.setInquiry(inquiry);
        inquiryFeedback.setRespondentUser(userRepository.findById(inquiryFeedbackDto.getRespondentUserId()).orElseThrow(RuntimeException::new));
        inquiryFeedback.setFeedbackDetails(inquiryFeedbackDto.getFeedbackDetails());
        inquiryFeedback.setSentViaEmail(inquiryFeedbackDto.isSentViaEmail());
        inquiryFeedback.setCreatedBy("dawen"); //TODO
        inquiryFeedback.setCreatedDate(LocalDateTime.now());
        return inquiryFeedback;
    }
}
