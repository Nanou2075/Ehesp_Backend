package com.elearning.elearning.admin;

import com.elearning.elearning.exception.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
    private String mail;
    private String phone;
    private Permission permission;
    private String fullName;
    private String sex;

}
