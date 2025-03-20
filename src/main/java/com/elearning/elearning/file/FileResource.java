package com.elearning.elearning.file;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequestMapping("file/")
public interface FileResource {


    @PostMapping("change/{idFile}")
    Response change(@PathVariable String idFile, MultipartFile file) throws IOException;

    @GetMapping("module")
    Response getFileByModule() throws IOException;

    @GetMapping("module/{id}")
    Response getFileByModuleId(@PathVariable String id) throws IOException;

    @GetMapping(value = "{id}",produces ={MediaType.APPLICATION_PDF_VALUE})
    byte[]readFile(@PathVariable String id) throws IOException;


    @GetMapping("all")
    Response   getFileAll() throws IOException;

    @DeleteMapping("{id}")
    Response  delete(@PathVariable String id) throws IOException;
}
