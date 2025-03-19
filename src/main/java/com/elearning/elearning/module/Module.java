package com.elearning.elearning.module;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.speciality.Speciality;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Module extends BaseEntity {
    private String name;
    @ManyToOne
    private Speciality speciality;
    @ManyToOne
    private Teacher coordinator;
    @ManyToOne
    private Teacher teacher;
}
