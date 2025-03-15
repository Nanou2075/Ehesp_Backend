package com.elearning.elearning.module;

import com.elearning.elearning.training.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ModuleRepository extends JpaRepository<Module, String> {
    Optional<Module> findByNameIgnoreCase(String name);
    Set<Module> findAllByTraining(Training training);
}
