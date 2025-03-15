package com.elearning.elearning.teacher;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public interface TeacherService {
    Response save(MultipartFile cv, Teacher teacher) throws IOException;

    Response update(String id, Teacher teacher);

    Response delete(String id);

    Response get(String id);

    Response getAll(int page, int size);

    Response search(int page, int size, String key);
}
