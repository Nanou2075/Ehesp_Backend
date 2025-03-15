package com.elearning.elearning.admin;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private String mail;
    private String phone;
    private String password;
    private String fullName;
    private String name;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;

}
