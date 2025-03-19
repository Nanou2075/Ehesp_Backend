package com.elearning.elearning.module;

import com.elearning.elearning.speciality.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ModuleRepository extends JpaRepository<Module, String> {
    Optional<Module> findByNameIgnoreCase(String name);
    Set<Module> findAllBySpeciality(Speciality speciality);
}
