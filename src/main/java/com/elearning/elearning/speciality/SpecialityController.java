package com.elearning.elearning.speciality;


import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.speciality.SpecialityMessage.*;


@RestController
@RequiredArgsConstructor
public class SpecialityController implements SpecialityResource {
private final ISpecialityService trainingService;
private final LocalService localService;

    @Override
    public Response addTraining(Speciality speciality) {
        trainingService.addTraining(speciality);
        return new Response(OK,localService.getMessage(TRAINING_SUCCESS));
    }

    @Override
    public Response updateTraining(String id, Speciality speciality) {
        trainingService.updateTraining(id, speciality);
        return new Response(OK,localService.getMessage(TRAINING_UPDATE));
    }

    @Override
    public Response removeTraining(String id) {
        trainingService.removeTraining(id);
        return new Response(OK,localService.getMessage(TRAINING_DELETE));
    }

    @Override
    public Response getTrainingByDomain(Domain domain) {
        return new Response(OK, trainingService.getAllByDomain(domain));
    }

    @Override
    public Response getTraining(String id) {
        return new Response(OK, trainingService.getTraining(id));

    }

    @Override
    public Response getAllTraining() {
        return new Response(OK, trainingService.getAll());
    }

    @Override
    public Response getTrainingStatical() {
        return new Response(OK, trainingService.getTrainingStatical());
    }
}
