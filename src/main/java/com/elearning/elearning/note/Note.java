package com.elearning.elearning.note;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.student.Student;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.training.Training;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Note extends BaseEntity {
    private float value;
    private String type;
    @OneToOne
    private Student student;
}
