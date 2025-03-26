package com.elearning.elearning.module;

import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleRequest {
    private String name;
    private Set<Speciality> speciality;
    private Teacher teacher;



}
