package com.elearning.elearning.security.token;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class TokenUtils {
    final Instant currentTime = Instant.now();
    final int access = 3600 * 60 * 1000;
    final int refresh = 30 * 60 * 60 * 1000;
}
