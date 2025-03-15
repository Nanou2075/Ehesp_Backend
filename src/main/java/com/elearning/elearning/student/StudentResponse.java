package com.elearning.elearning.student;


import com.elearning.elearning.document.IDocumentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String mail;
    private double balance;
    private String phone;
    private String password;
    private String name;
    private IDocumentResponse file;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;


}
