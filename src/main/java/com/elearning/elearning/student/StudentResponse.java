package com.elearning.elearning.student;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private String id;
    private String mail;
    private double balance;
    private String phone;
    private boolean available;
    private String fullName;
    private LocalDate birthday;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;


}
