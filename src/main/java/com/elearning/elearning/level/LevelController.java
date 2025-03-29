package com.elearning.elearning.level;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.speciality.SpecialityMessage.*;


@RestController
@RequiredArgsConstructor
public class LevelController implements LevelResource {
private final ILevelService levelService;
private final LocalService localService;

    @Override
    public Response addTraining(Level level) {
        levelService.addTraining(level);
        return new Response(OK,localService.getMessage(TRAINING_SUCCESS));
    }

    @Override
    public Response updateTraining(String id, Level level) {
        levelService.updateTraining(id, level);
        return new Response(OK,localService.getMessage(TRAINING_UPDATE));
    }

    @Override
    public Response removeTraining(String id) {
        levelService.removeTraining(id);
        return new Response(OK,localService.getMessage(TRAINING_DELETE));
    }



    @Override
    public Response getTraining(String id) {
        return new Response(OK, levelService.getTraining(id));

    }


    @Override
    public Response getAllTraining() {
        return new Response(OK, levelService.getAll());
    }

    @Override
    public Response getTrainingStatical() {
        return new Response(OK, levelService.getLevelStatical());
    }
}
