package com.elearning.elearning.appreciation;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AppreciationController implements AppreciationResource {

    private final AppreciationService appreciationService;

    @Override
    public Response save(Appreciation appreciation) {
        return appreciationService.save(appreciation);
    }

    @Override
    public Response update(String id, Appreciation appreciation) {
        return appreciationService.update(id, appreciation);
    }

    @Override
    public Response get(String id) {
        return appreciationService.get(id);
    }

    @Override
    public Response getAll() {
        return appreciationService.getAll();
    }

    @Override
    public Response getStudentMemory() {
        return appreciationService.getStudentMemory();
    }

    @Override
    public Response delete(String id) {
        return appreciationService.delete(id);
    }

}


