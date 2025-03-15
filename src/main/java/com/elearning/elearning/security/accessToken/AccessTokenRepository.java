package com.elearning.elearning.security.accessToken;

import com.elearning.elearning.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {

    Optional<AccessToken> findByValue(String value);

    Optional<AccessToken> findByAccount(Account account);

    void deleteAllByAccount(Account username);
}
