package ph.gov.bbop.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ph.gov.bbop.application.dto.ApplicationDocumentDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.model.ApplicationDocument;
import ph.gov.bbop.application.repository.ApplicationDocumentRepository;
import ph.gov.bbop.application.repository.ApplicationRepository;
import ph.gov.bbop.application.util.ApplicationDocumentMapper;
import ph.gov.bbop.common.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ApplicationDocumentService {

    private final ApplicationDocumentRepository applicationDocumentRepository;
    private final ApplicationDocumentMapper applicationDocumentMapper;
    private final ApplicationRepository applicationRepository;

    @Value("${app.documents.dir}")
    private String DOCUMENT_BASE_FOLDER;

    public ApplicationDocumentService(ApplicationDocumentRepository applicationDocumentRepository, ApplicationDocumentMapper applicationDocumentMapper, ApplicationRepository applicationRepository) {
        this.applicationDocumentRepository = applicationDocumentRepository;
        this.applicationDocumentMapper = applicationDocumentMapper;
        this.applicationRepository = applicationRepository;
    }

    public List<ApplicationDocumentDto> findAll(Long applId, int size, int limit) {
        Page<ApplicationDocument> pagedApplicationDocuments = applicationDocumentRepository.findByApplicationOrderByCreatedDateDesc(new Application(applId), PageRequest.of(size, limit));
        return applicationDocumentMapper.toDto(pagedApplicationDocuments.getContent());
    }

    public long countByApplication(Long applId) {
        return applicationDocumentRepository.countByApplication(new Application(applId));
    }

    public ApplicationDocumentDto findById(Long id) {
        return applicationDocumentMapper.toDto(applicationDocumentRepository.findById(id).orElse(null));
    }

    public ApplicationDocumentDto create(ApplicationDocumentDto applicationDocumentDto) {
        log.debug("ApplicationDocumentService | create | applicationDocumentDto: {}", applicationDocumentDto);
        if (applicationDocumentDto.getId() != null && applicationDocumentRepository.existsById(applicationDocumentDto.getId())) {
            throw new RuntimeException("Application Document already exists.");
        }
        try {
            String documentPath = uploadFileToServer(applicationDocumentDto.getApplId(), applicationDocumentDto.getDocumentFile());
            applicationDocumentDto.setDocumentName(applicationDocumentDto.getDocumentFile().getOriginalFilename());
            applicationDocumentDto.setDocumentPath(documentPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Application application = applicationRepository.findById(applicationDocumentDto.getApplId()).orElseThrow();
        return applicationDocumentMapper.toDto(
                applicationDocumentRepository.save(
                        applicationDocumentMapper.toEntity(applicationDocumentDto, application)
                )
        );
    }

    public ApplicationDocumentDto delete(Long id) {
        ApplicationDocument applicationDocument = applicationDocumentRepository.findById(id).orElseThrow();
        deleteFileFromServer(applicationDocument.getDocumentPath());
        ApplicationDocumentDto applicationDocumentDto = applicationDocumentMapper.toDto(applicationDocument);
        applicationDocumentRepository.deleteById(id);
        return applicationDocumentDto;
    }

    private String uploadFileToServer(Long applId, MultipartFile uploadedFile) throws IOException {
        File folder = new File(DOCUMENT_BASE_FOLDER + File.separator + applId.toString());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder.getAbsolutePath() + File.separator + uploadedFile.getOriginalFilename());
        if (!file.exists()) {
            file.createNewFile();
        }
        uploadedFile.transferTo(file);
        return FileUtil.encodeDocumentPath(file.getAbsolutePath());
    }

    private void deleteFileFromServer(String documentPath) {
        File file = new File(documentPath);
        if (file.exists()) {
            file.delete();
        }
    }

}
