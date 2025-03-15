package com.elearning.elearning.section;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SectionMapper {

    public SectionRequest toDTO(Section entity) {
        if (entity == null) {
            return null;
        }
        SectionRequest dto = new SectionRequest();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Section toEntity(SectionRequest dto) {
        if (dto == null) {
            return null;
        }
        Section entity = new Section();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }


}
