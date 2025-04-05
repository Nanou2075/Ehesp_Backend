package com.elearning.elearning.appreciation;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.student.Student;
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
public class Appreciation extends BaseEntity {
    private String description;
    private String mention;
    @OneToOne
    private Student student;
}
