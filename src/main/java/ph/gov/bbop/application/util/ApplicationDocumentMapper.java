package ph.gov.bbop.application.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.application.dto.ApplicationDocumentDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.model.ApplicationDocument;
import ph.gov.bbop.code.model.Code;
import ph.gov.bbop.code.util.CodeUtil;
import ph.gov.bbop.common.CommonConstants;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationDocumentMapper {

    private final CodeUtil codeUtil;

    public ApplicationDocumentMapper(CodeUtil codeUtil) {
        this.codeUtil = codeUtil;
    }

    public List<ApplicationDocumentDto> toDto(List<ApplicationDocument> applicationDocuments) {
        return applicationDocuments.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ApplicationDocumentDto toDto(ApplicationDocument applicationDocument) {
        ApplicationDocumentDto applicationDocumentDto = new ApplicationDocumentDto();
        if (applicationDocument == null) {
            return applicationDocumentDto;
        }
        applicationDocumentDto.setId(applicationDocument.getId());
        applicationDocumentDto.setApplId(applicationDocument.getApplication().getId());
        Code documentTypeCode = codeUtil.getCode(CommonConstants.CC_DOCUMENT_TYPE, applicationDocument.getDocumentType());
        applicationDocumentDto.setDocumentType(documentTypeCode.getCode());
        applicationDocumentDto.setDocumentTypeDescr(documentTypeCode.getCodeDescr());
        applicationDocumentDto.setDocumentName(applicationDocument.getDocumentName());
        applicationDocumentDto.setDocumentPath(applicationDocument.getDocumentPath());
        return applicationDocumentDto;
    }

    public ApplicationDocument toEntity(ApplicationDocumentDto applicationDocumentDto, Application application) {
        return toEntity(applicationDocumentDto, null, application);
    }

    public ApplicationDocument toEntity(ApplicationDocumentDto applicationDocumentDto, ApplicationDocument applicationDocument, Application application) {
        if (applicationDocument == null) {
            applicationDocument = new ApplicationDocument();
        }
        applicationDocument.setApplication(application);
        applicationDocument.setDocumentType(codeUtil.getCode(CommonConstants.CC_DOCUMENT_TYPE, applicationDocumentDto.getDocumentType()).getCode());
        applicationDocument.setDocumentName(applicationDocumentDto.getDocumentName());
        applicationDocument.setDocumentPath(applicationDocumentDto.getDocumentPath());
        return applicationDocument;
    }
}