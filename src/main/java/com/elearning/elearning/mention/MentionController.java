package com.elearning.elearning.mention;


import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.level.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.mention.MentionMessage.*;


@RestController
@RequiredArgsConstructor
public class MentionController implements MentionResource {
private final IMentionService mentionService;
private final LocalService localService;

    @Override
    public Response addTraining(Mention mention) {
        mentionService.addTraining(mention);
        return new Response(OK,localService.getMessage(TRAINING_SUCCESS));
    }

    @Override
    public Response updateTraining(String id, Mention mention) {
        mentionService.updateTraining(id, mention);
        return new Response(OK,localService.getMessage(TRAINING_UPDATE));
    }

    @Override
    public Response removeTraining(String id) {
        mentionService.removeTraining(id);
        return new Response(OK,localService.getMessage(TRAINING_DELETE));
    }

    @Override
    public Response getMentionByDomain(Domain domain) {
        return new Response(OK, mentionService.getAllByDomain(domain));
    }

    @Override
    public Response getTraining(String id) {
        return new Response(OK, mentionService.getTraining(id));

    }

    @Override
    public Response getAllTraining() {
        return new Response(OK, mentionService.getAll());
    }

    @Override
    public Response getTrainingStatical() {
        return new Response(OK, mentionService.getTrainingStatical());
    }
}
