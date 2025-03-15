package com.elearning.elearning.teacher;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeacherController implements TeacherResource {
    private final TeacherService teacherService;


    @Override
    public Response save( Teacher teacher ) {
        return teacherService.save(teacher);
    }

    @Override
    public Response update(String id,  Teacher teacher) {
        return teacherService.update(id, teacher);
    }

    @Override
    public Response get(String id) {
        return teacherService.get(id);
    }

    @Override
    public Response search(int page, int size, String keyword) {
        return teacherService.search(page, size, keyword);
    }

    @Override
    public Response getAll(int page, int size) {
        return teacherService.getAll(page, size);
    }

    @Override
    public Response delete(String id) {
        return teacherService.delete(id);
    }

}

