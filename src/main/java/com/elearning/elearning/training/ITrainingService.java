package com.elearning.elearning.training;

import com.elearning.elearning.domain.Domain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Transactional
@Service
public interface ITrainingService {
    void addTraining(Training training);
    void updateTraining(String id, Training training);
    Training getTraining(String id);
    void removeTraining(String id);
    Set<TrainingResponse> getAll();

    Set<TrainingResponse> getAllByDomain(Domain domain);
}
