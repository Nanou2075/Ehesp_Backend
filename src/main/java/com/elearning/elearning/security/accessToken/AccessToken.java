package com.elearning.elearning.security.accessToken;

import com.elearning.elearning.account.Account;
import com.elearning.elearning.security.refreshToken.RefreshToken;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "access_token", indexes = {
        @Index(columnList = "value, account_id", unique = true),
})
public class AccessToken {
    @Id
    @UuidGenerator()
    @Column(nullable = false, updatable = false)
    private String id;
    @Column(columnDefinition = "TEXT")
    private String value;
    private Instant expireTime;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
    private RefreshToken refreshToken;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private Account account;


}
