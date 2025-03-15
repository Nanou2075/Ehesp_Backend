package com.elearning.elearning.video;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequestMapping("video/")
public interface VideoResource {


    @PostMapping("change/{idFile}")
    Response change(@PathVariable String idFile, MultipartFile file) throws IOException;



    @GetMapping(value = "read/{id}",produces = "video/mp4")
    Mono<Resource> readVideo(@PathVariable String id) throws IOException;

    @GetMapping("all")
    Response   getVideoAll() throws IOException;
}
