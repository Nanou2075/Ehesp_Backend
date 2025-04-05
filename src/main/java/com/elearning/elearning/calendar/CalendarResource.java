package com.elearning.elearning.calendar;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("calendar/")
public interface CalendarResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid Calendar calendar);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody Calendar calendar);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("all")
    Response getAll();

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);


}
