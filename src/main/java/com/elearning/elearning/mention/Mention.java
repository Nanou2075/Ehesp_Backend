package com.elearning.elearning.mention;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.level.Level;
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
public class Mention extends BaseEntity {
    private String name;
    private BigDecimal price;
    @ManyToOne
    private Domain domain;

}
