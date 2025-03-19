package com.elearning.elearning.speciality;


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
import static com.elearning.elearning.speciality.SpecialityMessage.*;


@Transactional
@RequiredArgsConstructor
@Service
public class SpecialityService implements ISpecialityService {
    private final SpecialityRepository specialityRepository;
    private final StudentRepository studentRepository;

    private final LocalService localService;


    /**
     *
     * @param speciality the value to save in DB
     */

    @Override
    public void addTraining(Speciality speciality) {
        if (specialityRepository.findByNameIgnoreCase(speciality.getName()).isPresent()){
           throw new AlreadyExistException(NO,localService.getMessage(TRAINING_EXIT));}
            specialityRepository.save(speciality);
    }




    @Override
    public Set<SpecialityValue> getTrainingStatical() {
        Set<SpecialityValue> values = new HashSet<>();
        specialityRepository.findAll().forEach(training -> {
            values.add(SpecialityValue.builder()
                    .name(training.getName())
                    .number(studentRepository.findAllBySpeciality(training).isEmpty() ? NO: studentRepository.findAllBySpeciality(training).size())
                    .build());


        });

return values;
    }


    /**
     *
     * @param id permit to get the Module
     * @param speciality the value to set the current speciality data
     */

    @Override
    public void  updateTraining(String id, Speciality speciality) {
         specialityRepository.findById(id).ifPresentOrElse(local ->{
                    speciality.setName(speciality.getName());
                    specialityRepository.save(local);},
                 ()-> {throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));});}

    /**
     *
     * @param id permit to get the Module
     * @return  Module
     */
    @Override
    public Speciality getTraining(String id) {
        return specialityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND)));
    }

    /**
     *
     * @param id  permit to get the Module and delete
     */

    @Override
    public void removeTraining(String id) {
        specialityRepository.findById(id).ifPresentOrElse(specialityRepository::delete,()->{
            throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));
        });

    }

    @Override
    public Set<SpecialityResponse> getAll() {
        if (specialityRepository.findAll().isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(specialityRepository.findAll())
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }

    public Set<SpecialityResponse> convertToResponse(List<Speciality> specialityList) {
        Set<SpecialityResponse> specialityResponseList = new HashSet<>();
        specialityList.forEach(training -> {
            specialityResponseList.add(SpecialityResponse.builder()
                            .id(training.getId())
                            .name(training.getName())
                            .price(training.getPrice())
                            .coordinator(training.getCoordinator())
                    .build());
        });
        return specialityResponseList;
    }


    @Override
    public Set<SpecialityResponse> getAllByDomain(Domain domain) {
        if (specialityRepository.findAllByMentionDomain(domain).isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(specialityRepository.findAllByMentionDomain(domain))
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }
}



