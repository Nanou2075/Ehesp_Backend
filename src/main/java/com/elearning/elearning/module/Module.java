package com.elearning.elearning.module;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.training.Training;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Module extends BaseEntity {
    private String name;
    @ManyToOne
    private Training training;
    @ManyToOne
    private Teacher coordinator;
    @ManyToOne
    private Teacher teacher;
}
