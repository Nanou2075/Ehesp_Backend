package com.elearning.elearning.training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, String> {
    Optional<Training> findByNameIgnoreCase(String name);
}
