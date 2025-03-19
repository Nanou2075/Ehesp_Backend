package com.elearning.elearning.domain;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.level.Level;
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
public class Domain extends BaseEntity {
    private String name;
    @ManyToOne
    private Level level;

}
