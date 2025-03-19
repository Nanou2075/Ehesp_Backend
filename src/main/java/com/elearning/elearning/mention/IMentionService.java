package com.elearning.elearning.mention;

import com.elearning.elearning.domain.Domain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Transactional
@Service
public interface IMentionService {
    void addTraining(Mention mention);
    Set<MentionValue> getTrainingStatical();
    void updateTraining(String id, Mention mention);
    Mention getTraining(String id);
    void removeTraining(String id);
    Set<MentionResponse> getAll();

    Set<MentionResponse> getAllByDomain(Domain domain);
}
