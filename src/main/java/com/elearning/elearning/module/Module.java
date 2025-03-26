package com.elearning.elearning.module;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.speciality.Speciality;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Module extends BaseEntity {
    private String name;
    @ManyToMany
    private Set<Speciality> speciality;
    @ManyToOne
    private Teacher teacher;
}
