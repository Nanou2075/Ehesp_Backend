package com.elearning.elearning.cover;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.module.Module;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("cover/")
public interface CoverResource {

    @GetMapping(value = "{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    byte[] getFile(@PathVariable String id) throws IOException;

    @PostMapping("upload")
    Response updateCover (@RequestParam("file") MultipartFile file,@RequestBody Module module) throws IOException;


}
