package com.elearning.elearning.exam;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("exam/")
public interface ExamResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid Exam exam);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody Exam exam);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("all")
    Response getAll();


    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);


}
