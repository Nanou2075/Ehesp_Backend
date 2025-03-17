package com.elearning.elearning.memory;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface MemoryService {
    Response save(Memory memory);

    Response update(String id, Memory memory);

    Response delete(String id);

    Response get(String id);

    Response getAll();


    /*
    recuperation de la liste   des sections
    */
    Response getStudentMemory();
}
