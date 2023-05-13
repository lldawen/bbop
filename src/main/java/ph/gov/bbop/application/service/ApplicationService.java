package ph.gov.bbop.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ph.gov.bbop.application.dto.ApplicationDto;
import ph.gov.bbop.application.model.Application;
import ph.gov.bbop.application.repository.ApplicationRepository;
import ph.gov.bbop.application.util.ApplicationMapper;
import ph.gov.bbop.code.model.Code;
import ph.gov.bbop.code.util.CodeUtil;
import ph.gov.bbop.common.CommonConstants;
import ph.gov.bbop.user.model.User;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final CodeUtil codeUtil;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, CodeUtil codeUtil) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.codeUtil = codeUtil;
    }

    public List<ApplicationDto> findAll() {
        return applicationMapper.toDto(applicationRepository.findAll());
    }

    public List<ApplicationDto> findAll(String userId, int size, int limit) {
        Page<Application> pagedApplications = applicationRepository.findByApplicantAndStatusNot(new User(userId), CommonConstants.APPL_STATUS_DELETED, PageRequest.of(size, limit));
        return applicationMapper.toDto(pagedApplications.getContent());
    }

    public long getCount(String userId) {
        return applicationRepository.countByApplicant(new User(userId));
    }

    public ApplicationDto findById(Long id) {
        return applicationMapper.toDto(applicationRepository.findById(id).orElse(null));
    }

    public ApplicationDto create(ApplicationDto applicationDto) {
        if (applicationDto.getId() != null && applicationRepository.existsById(applicationDto.getId())) {
            throw new RuntimeException("Application already exists.");
        }
        applicationDto.setStatus(CommonConstants.APPL_STATUS_DRAFT);
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public ApplicationDto update(Long id, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(id).orElseThrow();
        return applicationMapper.toDto(applicationRepository.save(applicationMapper.toEntity(applicationDto)));
    }

    public ApplicationDto submit(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        Code code = codeUtil.getCode(CommonConstants.CC_APPL_STATUS, CommonConstants.APPL_STATUS_OPEN);
        application.setStatus(code.getCode());
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public ApplicationDto delete(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_DELETED);
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    public ApplicationDto withdraw(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(CommonConstants.APPL_STATUS_WITHDRAWN);
        applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }
}
