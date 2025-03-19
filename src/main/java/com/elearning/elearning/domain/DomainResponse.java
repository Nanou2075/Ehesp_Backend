package com.elearning.elearning.domain;

import com.elearning.elearning.level.Level;
import com.elearning.elearning.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomainResponse {
    private String id;
    private String name;
    private Level level;


}
