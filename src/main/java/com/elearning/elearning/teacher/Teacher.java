package com.elearning.elearning.teacher;

import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.common.UserBaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends BaseEntity {
    private String title;
    private String level;
    private String mail;
    private String phone;
    private String sex;
    private String fullName;


}
