package com.elearning.elearning.account;

import com.elearning.elearning.common.UserBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.Cache;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT", length = 20)
@Entity
@Table(indexes = {@Index(name = "index_account",columnList = "phone, mail", unique = true)})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Account extends UserBaseEntity {
    private boolean available= true;
    private String country;
    private String mail;
    private String sex;
    private LocalDate birthday;

}
