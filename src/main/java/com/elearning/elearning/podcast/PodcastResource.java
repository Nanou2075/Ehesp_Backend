package com.elearning.elearning.podcast;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequestMapping("podcast/")
public interface PodcastResource {

    @PostMapping("change")
    Response change(@RequestBody String idFile, MultipartFile file) throws IOException;

    @PostMapping("module")
    Response getPodcastByModule() throws IOException;

    @GetMapping(value = "{id}",produces = "video/mp4")
    Mono<Resource> readPodcast(@PathVariable String id) throws IOException;
}
