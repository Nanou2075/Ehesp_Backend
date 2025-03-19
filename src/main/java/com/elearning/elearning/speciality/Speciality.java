package com.elearning.elearning.speciality;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.mention.Mention;
import com.elearning.elearning.teacher.Teacher;
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
public class Speciality extends BaseEntity {
    private String name;
    private BigDecimal price;
    @ManyToOne
    private Mention mention;
    @ManyToOne
    private Teacher coordinator;
}
