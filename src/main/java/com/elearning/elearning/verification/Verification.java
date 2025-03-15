package com.elearning.elearning.verification;

import com.elearning.elearning.account.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Verification {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    private Instant createdDate;
    private Instant activatedDate;
    private Instant expiredDate;
    private String code;
    private boolean cheeked = false;
    private Boolean generate = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
