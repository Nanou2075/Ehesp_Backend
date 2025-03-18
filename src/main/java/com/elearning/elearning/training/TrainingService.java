package com.elearning.elearning.training;


import com.elearning.elearning.domain.Domain;
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
import static com.elearning.elearning.training.TrainingMessage.*;


@Transactional
@RequiredArgsConstructor
@Service
public class TrainingService implements ITrainingService {
    private final TrainingRepository trainingRepository;
    private final StudentRepository studentRepository;

    private final LocalService localService;


    /**
     *
     * @param training the value to save in DB
     */

    @Override
    public void addTraining(Training training) {
        if (trainingRepository.findByNameIgnoreCase(training.getName()).isPresent()){
           throw new AlreadyExistException(NO,localService.getMessage(TRAINING_EXIT));}
            trainingRepository.save(training);
    }




    @Override
    public Set<TrainingValue> getTrainingStatical() {
        Set<TrainingValue> values = new HashSet<>();
        trainingRepository.findAll().forEach(training -> {
            values.add(TrainingValue.builder()
                    .name(training.getName())
                    .number(studentRepository.findAllByTraining(training).isEmpty() ? NO: studentRepository.findAllByTraining(training).size())
                    .build());


        });

return values;
    }


    /**
     *
     * @param id permit to get the Module
     * @param training the value to set the current training data
     */

    @Override
    public void  updateTraining(String id, Training training) {
         trainingRepository.findById(id).ifPresentOrElse(local ->{
                    training.setName(training.getName());
                    trainingRepository.save(local);},
                 ()-> {throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));});}

    /**
     *
     * @param id permit to get the Module
     * @return  Module
     */
    @Override
    public Training getTraining(String id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND)));
    }

    /**
     *
     * @param id  permit to get the Module and delete
     */

    @Override
    public void removeTraining(String id) {
        trainingRepository.findById(id).ifPresentOrElse(trainingRepository::delete,()->{
            throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));
        });

    }

    @Override
    public Set<TrainingResponse> getAll() {
        if (trainingRepository.findAll().isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(trainingRepository.findAll())
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }

    public Set<TrainingResponse> convertToResponse(List<Training> trainingList) {
        Set<TrainingResponse> trainingResponseList = new HashSet<>();
        trainingList.forEach(training -> {
            trainingResponseList.add(TrainingResponse.builder()
                            .id(training.getId())
                            .name(training.getName())
                            .price(training.getPrice())
                            .coordinator(training.getCoordinator())
                    .build());
        });
        return trainingResponseList;
    }


    @Override
    public Set<TrainingResponse> getAllByDomain(Domain domain) {
        if (trainingRepository.findAllByDomain(domain).isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(trainingRepository.findAllByDomain(domain))
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }
}



