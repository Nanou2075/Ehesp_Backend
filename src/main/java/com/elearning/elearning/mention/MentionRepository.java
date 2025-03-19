package com.elearning.elearning.mention;

import com.elearning.elearning.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentionRepository extends JpaRepository<Mention, String> {
    Optional<Mention> findByNameIgnoreCase(String name);
    List<Mention> findAllByDomain(Domain domain);
}
