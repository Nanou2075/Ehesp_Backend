package com.elearning.elearning.memory;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.student.Student;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.training.Training;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Memory extends BaseEntity {
    private LocalDate date;
    private LocalDate time;
    private String content;
    @ManyToOne
    private Training training;
    @ManyToOne
    private Teacher frame;
    @OneToMany
    private Set<Teacher> jury;
    @ManyToOne
    private Student student;
}
