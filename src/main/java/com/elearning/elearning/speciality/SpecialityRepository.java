package com.elearning.elearning.speciality;

import com.elearning.elearning.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality, String> {
    Optional<Speciality> findByNameIgnoreCase(String name);
    List<Speciality> findAllByMentionDomain(Domain domain);
}
