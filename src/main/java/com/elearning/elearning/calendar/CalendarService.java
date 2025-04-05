package com.elearning.elearning.calendar;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CalendarService {
    Response save(Calendar calendar);

    Response update(String id, Calendar calendar);

    Response delete(String id);

    Response get(String id);

    Response getAll();

}
