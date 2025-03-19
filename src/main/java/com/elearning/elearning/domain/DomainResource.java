package com.elearning.elearning.domain;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.level.Level;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("domain/")
public interface DomainResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid Domain domain );

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody Domain domain);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @GetMapping("list")
    Response getAll();

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);

    @PostMapping("search")
    Response search (@RequestBody String key);
    @GetMapping("statistical")
    Response getDomainStatical();

    @PostMapping("level")
    Response getDomainByLevel (@RequestBody Level level);
}
