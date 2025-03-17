package com.elearning.elearning.memory;


import com.elearning.elearning.domain.DomainRequest;
import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("memory/")
public interface MemoryResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid Memory memory);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody Memory memory);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("all")
    Response getAll();
    @GetMapping("student")
    Response getStudentMemory();

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);


}
