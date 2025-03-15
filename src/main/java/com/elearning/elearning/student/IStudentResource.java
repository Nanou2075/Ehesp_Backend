package com.elearning.elearning.student;

import com.elearning.elearning.exception.Response.Response;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("student/")
public interface IStudentResource {

    @PostMapping(value = "save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response addStudent(@RequestPart(value = "file",required = false)  MultipartFile file,  @RequestPart StudentRequest request) throws MessagingException, IOException;

    @GetMapping("get/{id}")
    Response getStudentById(@PathVariable  String id);

    @DeleteMapping("delete/{id}")
    Response deleteStudentById (@PathVariable String id);

    @PutMapping("update/{id}")
    Response updateStudent (@PathVariable String id,@RequestBody StudentUpdate request);

    @GetMapping("all")
    Response getAllStudent(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                    @RequestParam(name = "size", defaultValue = "10", required = false) int size);

}
