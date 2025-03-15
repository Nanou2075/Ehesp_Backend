package com.elearning.elearning.training;

import com.elearning.elearning.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TrainingRepository extends JpaRepository<Training, String> {
    Optional<Training> findByNameIgnoreCase(String name);
    List<Training> findAllByDomain(Domain domain);
}
