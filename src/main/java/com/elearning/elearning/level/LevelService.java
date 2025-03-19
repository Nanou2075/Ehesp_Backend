package com.elearning.elearning.level;


import com.elearning.elearning.exception.AlreadyExistException;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.student.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.speciality.SpecialityMessage.*;


@Transactional
@RequiredArgsConstructor
@Service
public class LevelService implements ILevelService {
    private final LevelRepository levelRepository;
    private final StudentRepository studentRepository;

    private final LocalService localService;


    /**
     *
     * @param level the value to save in DB
     */

    @Override
    public void addTraining(Level level) {
        if (levelRepository.findByNameIgnoreCase(level.getName()).isPresent()){
           throw new AlreadyExistException(NO,localService.getMessage(TRAINING_EXIT));}
            levelRepository.save(level);
    }




    @Override
    public Set<LevelValue> getTrainingStatical() {
        Set<LevelValue> values = new HashSet<>();
        levelRepository.findAll().forEach(training -> {
            values.add(LevelValue.builder()
                    .name(training.getName())
                    .build());


        });

return values;
    }


    /**
     *
     * @param id permit to get the Module
     * @param level the value to set the current level data
     */

    @Override
    public void  updateTraining(String id, Level level) {
         levelRepository.findById(id).ifPresentOrElse(local ->{
                    level.setName(level.getName());
                    levelRepository.save(local);},
                 ()-> {throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));});}

    /**
     *
     * @param id permit to get the Module
     * @return  Module
     */
    @Override
    public Level getTraining(String id) {
        return levelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND)));
    }

    /**
     *
     * @param id  permit to get the Module and delete
     */

    @Override
    public void removeTraining(String id) {
        levelRepository.findById(id).ifPresentOrElse(levelRepository::delete,()->{
            throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));
        });

    }

    @Override
    public Set<LevelResponse> getAll() {
        if (levelRepository.findAll().isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(levelRepository.findAll())
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }

    public Set<LevelResponse> convertToResponse(List<Level> levelList) {
        Set<LevelResponse> levelResponseList = new HashSet<>();
        levelList.forEach(training -> {
            levelResponseList.add(LevelResponse.builder()
                            .id(training.getId())
                            .name(training.getName())

                    .build());
        });
        return levelResponseList;
    }



}



