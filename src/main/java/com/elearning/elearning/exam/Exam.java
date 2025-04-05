package com.elearning.elearning.exam;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.module.Module;
import com.elearning.elearning.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exam extends BaseEntity {
    private LocalDate date;
    private LocalTime time;
    private String description;
    @ManyToOne
    private Module module;
}
