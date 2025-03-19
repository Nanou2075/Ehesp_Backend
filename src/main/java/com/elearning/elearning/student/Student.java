package com.elearning.elearning.student;

import com.elearning.elearning.account.Account;
import com.elearning.elearning.document.Document;
import com.elearning.elearning.speciality.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("STUDENT")
@Entity
public class Student extends Account {
    @ManyToOne
    private Speciality speciality;
    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true)
    private Document document;



}
