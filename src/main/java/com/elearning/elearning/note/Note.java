package com.elearning.elearning.note;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.module.Module;
import com.elearning.elearning.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note extends BaseEntity {
    private float value;
    private String type;
    @ManyToOne
    private Module module;
    @ManyToOne
    private Student student;
}
