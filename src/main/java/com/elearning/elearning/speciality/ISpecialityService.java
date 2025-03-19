package com.elearning.elearning.speciality;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.mention.Mention;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Transactional
@Service
public interface ISpecialityService {
    void addTraining(Speciality speciality);
    Set<SpecialityValue> getTrainingStatical();
    void updateTraining(String id, Speciality speciality);
    Speciality getTraining(String id);
    void removeTraining(String id);
    Set<SpecialityResponse> getAll();

    Set<SpecialityResponse> getAllByDomain(Domain domain);

    Set<SpecialityResponse> getAllByMention(Mention mention);
}
