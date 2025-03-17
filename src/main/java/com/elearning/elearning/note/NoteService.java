package com.elearning.elearning.note;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface NoteService {
    Response save(Note note);

    Response update(String id, Note note);

    Response delete(String id);

    Response get(String id);

    Response getAll();


}
