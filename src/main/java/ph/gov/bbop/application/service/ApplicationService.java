package ph.gov.bbop.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.repository.ApplicationRepository;
import ph.gov.bbop.application.util.ApplicationMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    public List<ApplicationDto> findAll() {
        return applicationMapper.toDto(applicationRepository.findAll());
    }

    public List<ApplicationDto> findAll(int size, int limit) {
        Page<Application> pagedApplications = applicationRepository.findAll(PageRequest.of(size, limit));
        return applicationMapper.toDto(pagedApplications.getContent());
    }

    public long getCount() {
        return applicationRepository.count();
    }

    public ApplicationDto findById(Long id) {
        return applicationMapper.toDto(applicationRepository.findById(id).orElse(null));
    }

    public ApplicationDto create(ApplicationDto applicationDto) {
        if (applicationDto.getId() != null && applicationRepository.existsById(applicationDto.getId())) {
            throw new RuntimeException("Application already exists.");
        }
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public ApplicationDto update(Long id, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(id).orElseThrow();
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public ApplicationDto delete(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        ApplicationDto applicationDto = applicationMapper.toDto(application);
        applicationRepository.deleteById(id);
        return applicationDto;
    }
}
