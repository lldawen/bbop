package ph.gov.bbop.common.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.common.dto.AuditableFieldsDto;
import ph.gov.bbop.common.model.BaseModel;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public final class AuditableFieldsMapper {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    public void toDto(AuditableFieldsDto auditableFieldsDto, BaseModel baseModel) {
        auditableFieldsDto.setCreatedBy(baseModel.getCreatedBy());
        auditableFieldsDto.setCreatedDate(dateTimeFormatter.format(baseModel.getCreatedDate()));
        auditableFieldsDto.setModifiedBy(baseModel.getModifiedBy());
        auditableFieldsDto.setModifiedDate(dateTimeFormatter.format(baseModel.getModifiedDate()));
    }
}
