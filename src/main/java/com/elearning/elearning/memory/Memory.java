package com.elearning.elearning.memory;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.student.Student;
import com.elearning.elearning.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Memory extends BaseEntity {
    private LocalDate date;
    private LocalTime time;
    @ManyToOne
    private Speciality speciality;
    @ManyToOne
    private Teacher framer;
    @OneToMany
    private Set<Teacher> jury;
    @OneToOne
    private Student student;
    private  boolean valid ;
}
