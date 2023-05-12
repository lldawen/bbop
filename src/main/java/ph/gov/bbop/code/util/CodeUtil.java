package ph.gov.bbop.code.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ph.gov.bbop.code.dto.CodeDto;
import ph.gov.bbop.code.model.Code;
import ph.gov.bbop.code.repository.CodeRepository;

@Slf4j
@Component
public class CodeUtil {

    private final CodeRepository codeRepository;
    private final CodeMapper codeMapper;

    public CodeUtil(CodeRepository codeRepository, CodeMapper codeMapper) {
        this.codeRepository = codeRepository;
        this.codeMapper = codeMapper;
    }

    public Code getCode(String category, String code) {
        log.debug("CodeUtil | getCode | category: {} | code: {}", category, code);
        if (StringUtils.isEmpty(category) || StringUtils.isEmpty(code)) {
            return null;
        }
        return codeRepository.findByCategoryAndCode(category, code);
    }

    public CodeDto getCodeDto(String category, String code) {
        return codeMapper.toDto(getCode(category, code));
    }

    public String getCodeValue(String category, String code) {
        Code codeObj = getCode(category, code);
        return codeObj == null ? null : codeObj.getCode();
    }
}
