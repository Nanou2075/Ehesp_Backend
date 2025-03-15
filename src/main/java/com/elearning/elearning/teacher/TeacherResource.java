package com.elearning.elearning.teacher;


import com.elearning.elearning.exception.Response.Response;
import org.springframework.web.bind.annotation.*;

@RequestMapping("teacher/")
public interface TeacherResource {

    @PostMapping("save")
    Response save(@RequestBody Teacher teacher);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @RequestBody  Teacher teacher);

    @GetMapping("{id}")
    Response get(@PathVariable String id);

    @PostMapping("search")
    Response search (@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                     @RequestParam(name = "size", defaultValue = "50", required = false) int size,
                     @RequestBody String keyword);

    @GetMapping("liste")
    Response getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                    @RequestParam(name = "size", defaultValue = "50", required = false) int size);

    @DeleteMapping("{id}")
    Response delete(@PathVariable String id);
}
