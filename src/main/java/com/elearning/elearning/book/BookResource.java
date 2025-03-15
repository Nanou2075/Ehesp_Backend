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

    @GetMapping(value = "{id}",produces ={MediaType.APPLICATION_PDF_VALUE})
    byte[] getBook(@PathVariable String id) throws IOException;


}
