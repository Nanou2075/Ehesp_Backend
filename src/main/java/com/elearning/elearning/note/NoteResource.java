package com.elearning.elearning.note;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("note/")
public interface NoteResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid Note note);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody Note note);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("all")
    Response getAll();

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);


}
