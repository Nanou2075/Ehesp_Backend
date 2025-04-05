package com.elearning.elearning.calendar;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CalendarController implements CalendarResource {

    private final CalendarService calendarService;

    @Override
    public Response save(Calendar calendar) {
        return calendarService.save(calendar);
    }

    @Override
    public Response update(String id, Calendar calendar) {
        return calendarService.update(id, calendar);
    }

    @Override
    public Response get(String id) {
        return calendarService.get(id);
    }

    @Override
    public Response getAll() {
        return calendarService.getAll();
    }


    @Override
    public Response delete(String id) {
        return calendarService.delete(id);
    }

}


