package com.elearning.elearning.student;

import com.elearning.elearning.account.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("STUDENT")
@Entity
public class Student extends Account {

}
