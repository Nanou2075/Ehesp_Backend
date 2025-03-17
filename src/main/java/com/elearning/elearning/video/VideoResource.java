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

    @GetMapping("module")
    Response getVideoByModule() throws IOException;

    @GetMapping("module/{id}")
    Response getVideoByModuleId(@PathVariable String id) throws IOException;

    @GetMapping(value = "{id}",produces = "video/mp4")
    Mono<Resource> readVideo(@PathVariable String id) throws IOException;


    @GetMapping("all")
    Response   getVideoAll() throws IOException;

    @DeleteMapping("{id}")
    Response  delete(@PathVariable String id) throws IOException;
}
