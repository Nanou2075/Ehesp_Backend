package com.elearning.elearning.exam;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.security.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.note.NoteMessage.*;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final AuthenticationService authenticationService;






     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(Exam exam) {
        examRepository.save(exam);
        return new Response(OK, NOTE_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Exam value) {
        Exam exam = examRepository.findById(id);
        examRepository.save(exam);
        return new Response(OK, NOTE_UPDATE);
    }



      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Exam exam = examRepository.findById(id);
        if (exam == null) {
            throw new NotFoundException(NO, NOTE_NO_EXIT);
        }
        examRepository.delete(exam);
        return new Response(OK, NOTE_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Exam exam = examRepository.findById(id);
        if (exam == null) {
            throw new NotFoundException(NO, NOTE_NO_EXIT);
        }
        return new Response(OK, exam);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Exam> exams = examRepository.findAll(Sort.by("createdDate").ascending());
        if (exams.isEmpty()) {
            throw new NotFoundException(NO, NOTE_EMPTY);
        }
        return new Response(OK, exams);
    }

    /*
recuperation de la liste   des sections
*/
    @Override
    public Response getAllByStudent() {
        List<Exam> exams = examRepository.findBySpeciality(authenticationService.currentSpeciality());
        if (exams.isEmpty()) {
            throw new NotFoundException(NO, NOTE_EMPTY);
        }
        return new Response(OK, exams);
    }


}
