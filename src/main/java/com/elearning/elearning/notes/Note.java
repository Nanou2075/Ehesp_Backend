package com.elearning.elearning.notes;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.module.Module;
import com.elearning.elearning.student.Student;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.training.Training;
import jakarta.persistence.*;
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
public class Note extends BaseEntity {
    @ManyToOne
    private Module training;
    @ManyToOne
    private Student student;
    private String remark;
    private String homework_grade;
    private String exam_grade;

}
