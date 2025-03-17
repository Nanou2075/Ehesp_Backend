package com.elearning.elearning.book;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("book/")
public interface BookResource {

    @PostMapping("change")
    Response changeBook(@RequestBody String idFile, MultipartFile file) throws IOException;

    @GetMapping("module/{id}")
    Response getBookByModuleId(@PathVariable String id) throws IOException;

    @GetMapping("module")
    Response getBookByModule() throws IOException;
    @DeleteMapping("{id}")
    Response  delete(@PathVariable String id) throws IOException;

    @GetMapping(value = "{id}",produces ={MediaType.APPLICATION_PDF_VALUE})
    byte[] getBook(@PathVariable String id) throws IOException;

    @GetMapping("all")
    Response   getBookAll() throws IOException;

}
