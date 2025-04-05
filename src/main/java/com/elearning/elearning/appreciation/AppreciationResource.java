package com.elearning.elearning.appreciation;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("appreciation/")
public interface AppreciationResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid Appreciation appreciation);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody Appreciation appreciation);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("all")
    Response getAll();
    @GetMapping("student")
    Response getStudentMemory();
    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);


}
