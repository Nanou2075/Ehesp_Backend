package com.elearning.elearning.section;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("section/")
public interface SectionResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid SectionRequest request);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody  SectionRequest request);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("list")
    Response getAll();

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);

    @PostMapping("search")
    Response search (@RequestBody String key);
}
