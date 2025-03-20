package com.elearning.elearning.mention;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MentionResponse {
    private String id;
    private String name;
    private Domain domain;
    private LocalDate createdDate;



}
