package com.elearning.elearning.student;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.speciality.Speciality;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("student/")
public interface IStudentResource {

    @PostMapping(value = "save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response addStudent(@RequestPart(value = "cv",required = false)  MultipartFile cv,@RequestPart(value = "degree",required = false)  MultipartFile file,  @RequestPart Student student) throws MessagingException, IOException;

    @GetMapping("get/{id}")
    Response getStudentById(@PathVariable  String id);

    @GetMapping("valid/{id}")
    Response validStudent(@PathVariable String id);

    @DeleteMapping("delete/{id}")
    Response deleteStudentById (@PathVariable String id);

    @PutMapping("update/{id}")
    Response updateStudent (@PathVariable String id,@RequestBody StudentUpdate request);

    @GetMapping("all")
    Response getAllStudent(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                    @RequestParam(name = "size", defaultValue = "10", required = false) int size);

    @PostMapping("speciality")
    Response getAllStudentBySpeciality( @RequestBody Speciality speciality);

}
