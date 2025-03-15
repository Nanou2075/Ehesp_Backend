package com.elearning.elearning.admin;


import com.elearning.elearning.account.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
@Entity
public class Admin extends Account {

}
