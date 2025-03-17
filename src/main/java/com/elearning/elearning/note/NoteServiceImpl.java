package com.elearning.elearning.note;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.note.NoteMessage.*;


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
        return new Response(OK, NOTE_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Note value) {
        Note note = noteRepository.findById(id);
        noteRepository.save(note);
        return new Response(OK, NOTE_UPDATE);
    }



      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Note note = noteRepository.findById(id);
        if (note == null) {
            throw new NotFoundException(NO, NOTE_NO_EXIT);
        }
        noteRepository.delete(note);
        return new Response(OK, NOTE_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Note note = noteRepository.findById(id);
        if (note == null) {
            throw new NotFoundException(NO, NOTE_NO_EXIT);
        }
        return new Response(OK, note);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Note> notes = noteRepository.findAll(Sort.by("createdDate").ascending());
        if (notes.isEmpty()) {
            throw new NotFoundException(NO, NOTE_EMPTY);
        }
        return new Response(OK, notes);
    }
}
