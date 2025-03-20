package com.elearning.elearning.speciality;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.level.Level;
import com.elearning.elearning.mention.Mention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SpecialityRepository extends JpaRepository<Speciality, String> {
    Optional<Speciality> findByNameIgnoreCase(String name);
    List<Speciality> findAllByMentionDomain(Domain domain);
    List<Speciality> findAllByMention(Mention mention);
    List<Speciality> findAllByMentionDomainLevel(Level level);

}
