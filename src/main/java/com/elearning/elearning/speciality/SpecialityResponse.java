package com.elearning.elearning.speciality;

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
public class SpecialityResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private Teacher coordinator;


}
