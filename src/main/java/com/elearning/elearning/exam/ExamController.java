package com.elearning.elearning.exam;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExamController implements ExamResource {

    private final ExamService examService;

    @Override
    public Response save(Exam exam) {
        return examService.save(exam);
    }

    @Override
    public Response update(String id, Exam exam) {
        return examService.update(id, exam);
    }

    @Override
    public Response get(String id) {
        return examService.get(id);
    }

    @Override
    public Response getAll() {
        return examService.getAll();
    }


    @Override
    public Response delete(String id) {
        return examService.delete(id);
    }

}


