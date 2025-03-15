package com.elearning.elearning.verification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, String> {
    Optional<Verification> findByCode(String token);
}
