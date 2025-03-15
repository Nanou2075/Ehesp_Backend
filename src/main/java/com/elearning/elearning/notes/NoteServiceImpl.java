package com.elearning.elearning.notes;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.memory.MemoryMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;






     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(Note note) {
        noteRepository.save(note);
        return new Response(OK, MEMORY_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Note value) {

        Note note = noteRepository.findById(id);
        noteRepository.save(note);
        return new Response(OK, MEMORY_UPDATE);
    }



      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Note note = noteRepository.findById(id);
        if (note == null) {
            throw new NotFoundException(NO, MEMORY_NO_EXIT);
        }
        noteRepository.delete(note);
        return new Response(OK, MEMORY_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Note note = noteRepository.findById(id);
        if (note == null) {
            throw new NotFoundException(NO, MEMORY_NO_EXIT);
        }
        return new Response(OK, note);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Note> memories = noteRepository.findAll(Sort.by("name").ascending());
        if (memories.isEmpty()) {
            throw new NotFoundException(NO, MEMORY_EMPTY);
        }
        return new Response(OK, memories);
    }
}
