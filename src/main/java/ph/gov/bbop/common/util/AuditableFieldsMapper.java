package ph.gov.bbop.common.util;

import org.springframework.stereotype.Component;
import ph.gov.bbop.common.dto.AuditableFieldsDto;
import ph.gov.bbop.common.model.BaseModel;

@Component
public final class AuditableFieldsMapper {

    public void toDto(AuditableFieldsDto auditableFieldsDto, BaseModel baseModel) {
        auditableFieldsDto.setCreatedBy(baseModel.getCreatedBy());
        auditableFieldsDto.setCreatedDate(DateTimeUtil.formatWithTime(baseModel.getCreatedDate()));
        auditableFieldsDto.setModifiedBy(baseModel.getModifiedBy());
        auditableFieldsDto.setModifiedDate(DateTimeUtil.formatWithTime(baseModel.getModifiedDate()));
    }
}
