package ph.gov.bbop.code.service;

import org.springframework.stereotype.Service;
import ph.gov.bbop.code.dto.CodeDto;
import ph.gov.bbop.code.model.Code;
import ph.gov.bbop.code.repository.CodeRepository;
import ph.gov.bbop.code.util.CodeMapper;

import java.util.List;

@Service
public class CodeService {

    private final CodeRepository codeRepository;
    private final CodeMapper codeMapper;

    public CodeService(CodeRepository codeRepository, CodeMapper codeMapper) {
        this.codeRepository = codeRepository;
        this.codeMapper = codeMapper;
    }

    public List<CodeDto> findAll() {
        return codeMapper.toDto(codeRepository.findAll());
    }

    public CodeDto findById(Long id) {
        return codeMapper.toDto(codeRepository.findById(id).orElse(null));
    }

    public List<CodeDto> findByCategory(String category) {
        return codeMapper.toDto(codeRepository.findByCategory(category));
    }

    public CodeDto create(CodeDto codeDto) {
        if (codeDto.getId() != null) {
            throw new RuntimeException("Code already exists.");
        }
        return save(codeDto);
    }

    public CodeDto update(Long id, CodeDto codeDto) {
        if (!codeRepository.existsById(id)) {
            throw new RuntimeException("Code does not exist.");
        }
        codeDto.setId(id);
        return save(codeDto);
    }

    private CodeDto save(CodeDto codeDto) {
        return codeMapper.toDto(codeRepository.save(codeMapper.toEntity(codeDto)));
    }

    public CodeDto delete(Long id) {
        Code code = codeRepository.findById(id).orElseThrow(RuntimeException::new);
        CodeDto codeDto = codeMapper.toDto(code);
        codeRepository.deleteById(id);
        return codeDto;
    }
}
