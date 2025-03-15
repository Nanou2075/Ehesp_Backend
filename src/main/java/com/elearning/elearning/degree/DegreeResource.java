package com.elearning.elearning.degree;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("degree/")
public interface DegreeResource {

    @GetMapping(value = "{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    byte[] getFile(@PathVariable String id) throws IOException;

    @PostMapping("upload")
    Response updateCV (@RequestParam("file") MultipartFile file) throws IOException;


}
