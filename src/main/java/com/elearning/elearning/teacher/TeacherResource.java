package com.elearning.elearning.teacher;


import com.elearning.elearning.exception.Response.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("teacher/")
public interface TeacherResource {

    @PostMapping(value = "save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response save(@RequestPart(value = "cv",required = false) MultipartFile cv,@RequestPart Teacher teacher) throws IOException;

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
