package com.elearning.elearning.mention;


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
public class MentionService implements IMentionService {
    private final MentionRepository mentionRepository;
    private final StudentRepository studentRepository;

    private final LocalService localService;


    /**
     *
     * @param mention the value to save in DB
     */

    @Override
    public void addTraining(Mention mention) {
        if (mentionRepository.findByNameIgnoreCase(mention.getName()).isPresent()){
           throw new AlreadyExistException(NO,localService.getMessage(TRAINING_EXIT));}
            mentionRepository.save(mention);
    }




    @Override
    public Set<MentionValue> getTrainingStatical() {
        Set<MentionValue> values = new HashSet<>();
        mentionRepository.findAll().forEach(training -> {
            values.add(MentionValue.builder()
                    .name(training.getName())
                    .build());


        });

return values;
    }


    /**
     *
     * @param id permit to get the Module
     * @param mention the value to set the current mention data
     */

    @Override
    public void  updateTraining(String id, Mention mention) {
         mentionRepository.findById(id).ifPresentOrElse(local ->{
                    mention.setName(mention.getName());
                    mentionRepository.save(local);},
                 ()-> {throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));});}

    /**
     *
     * @param id permit to get the Module
     * @return  Module
     */
    @Override
    public Mention getTraining(String id) {
        return mentionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND)));
    }

    /**
     *
     * @param id  permit to get the Module and delete
     */

    @Override
    public void removeTraining(String id) {
        mentionRepository.findById(id).ifPresentOrElse(mentionRepository::delete,()->{
            throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));
        });

    }

    @Override
    public Set<MentionResponse> getAll() {
        if (mentionRepository.findAll().isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(mentionRepository.findAll())
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }

    public Set<MentionResponse> convertToResponse(List<Mention> mentionList) {
        Set<MentionResponse> mentionResponseList = new HashSet<>();
        mentionList.forEach(training -> {
            mentionResponseList.add(MentionResponse.builder()
                            .id(training.getId())
                            .name(training.getName())
                            .price(training.getPrice())
                    .build());
        });
        return mentionResponseList;
    }


    @Override
    public Set<MentionResponse> getAllByDomain(Domain domain) {
        if (mentionRepository.findAllByDomain(domain).isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(mentionRepository.findAllByDomain(domain))
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }
}



