package com.elearning.elearning.module;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("module/")
public interface ModuleResource {
    @PostMapping(value = "save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response addModule(@RequestParam List<MultipartFile> files,@RequestParam MultipartFile file,@RequestPart Module module ) throws IOException;
    @PutMapping("{id}")
    Response updateModule(@PathVariable String id ,@RequestBody Module module);

    @DeleteMapping("{id}")
    Response removeModule(@PathVariable String id );

    @GetMapping("{id}")
    Response getModule(@PathVariable String id );

    @GetMapping("all")
    Response getAllModule();
}
