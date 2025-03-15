package com.elearning.elearning.notes;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoteController implements NoteResource {

    private final NoteService noteService;

    @Override
    public Response save(Note note) {
        return noteService.save(note);
    }

    @Override
    public Response update(String id, Note note) {
        return noteService.update(id, note);
    }

    @Override
    public Response get(String id) {
        return noteService.get(id);
    }

    @Override
    public Response getAll() {
        return noteService.getAll();
    }

    @Override
    public Response delete(String id) {
        return noteService.delete(id);
    }

}


