package com.elearning.elearning.security.refreshToken;

import com.elearning.elearning.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByValue(String value);

    Optional<RefreshToken> findByAccount(Account account);

    void deleteAllByAccount(Account username);
}
