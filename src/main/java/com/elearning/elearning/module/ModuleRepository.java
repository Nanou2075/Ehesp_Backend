package com.elearning.elearning.module;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, String> {
    Optional<Module> findByNameIgnoreCase(String name);
}
