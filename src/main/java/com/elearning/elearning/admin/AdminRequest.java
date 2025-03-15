package com.elearning.elearning.admin;

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
    private String password;
    private String fullName;
    private String logo;

}
