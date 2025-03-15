package com.elearning.elearning.year;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class YearMapper {

    public YearRequest toDTO(Year entity) {
        if (entity == null) {
            return null;
        }
        YearRequest dto = new YearRequest();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Year toEntity(YearRequest dto) {
        if (dto == null) {
            return null;
        }
        Year entity = new Year();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}
