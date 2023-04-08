package ph.gov.bbop.code.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.code.dto.CodeDto;
import ph.gov.bbop.code.model.Code;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeMapper {

    public List<CodeDto> toDto(List<Code> codeList) {
        return codeList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CodeDto toDto(Code code) {
        CodeDto codeDto = new CodeDto();
        if (code == null) {
            return codeDto;
        }
        codeDto.setId(code.getId());
        codeDto.setCategory(code.getCategory());
        codeDto.setCodeDescription(code.getCategoryDescr());
        codeDto.setCode(code.getCode());
        codeDto.setCodeDescription(code.getCodeDescr());
        codeDto.setActive(code.isActive());
        return codeDto;
    }

    public Code toEntity(CodeDto codeDto) {
        if (codeDto == null) {
            throw new RuntimeException("CodeDto must not be null.");
        }
        Code code = new Code();
        code.setId(codeDto.getId());
        code.setCategory(codeDto.getCategory());
        code.setCategoryDescr(codeDto.getCategoryDescription());
        code.setCode(codeDto.getCode());
        code.setCodeDescr(codeDto.getCodeDescription());
        code.setActive(codeDto.isActive());
        code.setCreatedBy("dawen"); //TODO
        code.setCreatedDate(LocalDateTime.now()); //TODO:
        return code;
    }
}
