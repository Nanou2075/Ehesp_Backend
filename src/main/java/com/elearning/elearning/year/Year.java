package com.elearning.elearning.year;

import com.elearning.elearning.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Year extends BaseEntity {
    private Boolean enabled = true;
    private String beginYear;
    private String endYear;
    private String currentYear;

}
