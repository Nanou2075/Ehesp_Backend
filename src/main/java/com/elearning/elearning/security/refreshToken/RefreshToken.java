package com.elearning.elearning.security.refreshToken;

import com.elearning.elearning.account.Account;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_token", indexes = {
        @Index(columnList = "value, account_id", unique = true),
})
public class RefreshToken {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    @Column(columnDefinition = "TEXT")
    private String value;
    private Instant expireTime;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private Account account;

}
