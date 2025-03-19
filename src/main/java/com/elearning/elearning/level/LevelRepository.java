package com.elearning.elearning.level;

import com.elearning.elearning.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, String> {
    Optional<Level> findByNameIgnoreCase(String name);
}
