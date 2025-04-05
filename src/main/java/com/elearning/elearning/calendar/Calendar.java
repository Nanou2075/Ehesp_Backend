package com.elearning.elearning.calendar;

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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Calendar extends BaseEntity {
    private int value;
    private String description;
    private LocalDate dateStart;
    private LocalDate dateEnd;
}
