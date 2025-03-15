package com.elearning.elearning.domain;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("domain/")
public interface DomainResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid DomainRequest request);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody DomainRequest request);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("list")
    Response getAll();

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);

    @PostMapping("search")
    Response search (@RequestBody String key);
}
