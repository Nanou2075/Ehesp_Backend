package com.elearning.elearning.appreciation;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface AppreciationService {
    Response save(Appreciation appreciation);

    Response update(String id, Appreciation appreciation);

    Response delete(String id);

    Response get(String id);

    Response getAll();


    /*
    recuperation de la liste   des sections
    */
    Response getStudentMemory();
}
