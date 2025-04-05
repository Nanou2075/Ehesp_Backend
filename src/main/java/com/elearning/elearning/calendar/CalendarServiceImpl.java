package com.elearning.elearning.calendar;


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
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final AuthenticationService authenticationService;






     /*
    Enregistrement  de la section
     */

    @Override
    public Response save(Calendar calendar) {
        calendarRepository.save(calendar);
        return new Response(OK, NOTE_SAVE);
    }


    /*
  Modification  de la section
   */
    @Override
    public Response update(String id, Calendar value) {
        Calendar calendar = calendarRepository.findById(id);
        calendarRepository.save(calendar);
        return new Response(OK, NOTE_UPDATE);
    }



      /*
    Suppression  de la matière
     */

    @Override
    public Response delete(String id) {
        Calendar calendar = calendarRepository.findById(id);
        if (calendar == null) {
            throw new NotFoundException(NO, NOTE_NO_EXIT);
        }
        calendarRepository.delete(calendar);
        return new Response(OK, NOTE_DELETE);
    }


    /*
  recuperation par id  de la matière
   */
    @Override
    public Response get(String id) {
        Calendar calendar = calendarRepository.findById(id);
        if (calendar == null) {
            throw new NotFoundException(NO, NOTE_NO_EXIT);
        }
        return new Response(OK, calendar);
    }


    /*
  recuperation de la liste   des sections
   */
    @Override
    public Response getAll() {
        List<Calendar> calendars = calendarRepository.findAll(Sort.by("createdDate").ascending());
        if (calendars.isEmpty()) {
            throw new NotFoundException(NO, NOTE_EMPTY);
        }
        return new Response(OK, calendars);
    }



}
