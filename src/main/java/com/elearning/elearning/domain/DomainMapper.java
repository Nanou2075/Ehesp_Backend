package com.elearning.elearning.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DomainMapper {

    public DomainRequest toDTO(Domain entity) {
        if (entity == null) {
            return null;
        }
        DomainRequest dto = new DomainRequest();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Domain toEntity(DomainRequest dto) {
        if (dto == null) {
            return null;
        }
        Domain entity = new Domain();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }


}
