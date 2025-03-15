package com.elearning.elearning.teacher;

import com.elearning.elearning.common.UserBaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends UserBaseEntity {
    private int hour;
    private int session;
    private int amount;


}
