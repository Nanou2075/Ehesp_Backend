package com.elearning.elearning.teacher;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface TeacherService {
    Response save( Teacher teacher);

    Response update(String id, Teacher teacher);

    Response delete(String id);

    Response get(String id);

    Response getAll(int page, int size);

    Response search(int page, int size, String key);
}
