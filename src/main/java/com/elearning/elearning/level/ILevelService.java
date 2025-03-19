package com.elearning.elearning.level;

import com.elearning.elearning.domain.Domain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Transactional
@Service
public interface ILevelService {
    void addTraining(Level level);
    Set<LevelValue> getTrainingStatical();
    void updateTraining(String id, Level level);
    Level getTraining(String id);
    void removeTraining(String id);
    Set<LevelResponse> getAll();

}
