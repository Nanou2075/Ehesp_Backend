package com.elearning.elearning.exam;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface ExamService {
    Response save(Exam exam);

    Response update(String id, Exam exam);

    Response delete(String id);

    Response get(String id);

    Response getAll();

    /*
    recuperation de la liste   des sections
    */
    Response getAllByStudent();
}
