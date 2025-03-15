package com.elearning.elearning.student;

import com.elearning.elearning.account.Account;
import com.elearning.elearning.document.Document;
import com.elearning.elearning.memory.Memory;
import com.elearning.elearning.training.Training;
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
    private Training training;
    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true)
    private Document document;



}
