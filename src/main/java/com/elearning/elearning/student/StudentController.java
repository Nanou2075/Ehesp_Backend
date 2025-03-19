package com.elearning.elearning.student;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.speciality.Speciality;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.AccountMessage.*;


@RestController
@RequiredArgsConstructor
public class StudentController implements IStudentResource {
    private final StudentService studentService;
    private final LocalService localService;

    @Override
    public Response addStudent(MultipartFile cv, MultipartFile degree,Student student) throws MessagingException, IOException {
        studentService.addStudent(cv,degree,student);
        return new Response(OK,localService.getMessage(ACCOUNT_SAVE));
    }

    @Override
    public Response getStudentById(String id) {
        return new Response(OK,studentService.getStudentById(id));
    }

    @Override
    public Response validStudent(String id) {
        studentService.validStudentBy(id);
        return new Response(OK,localService.getMessage(ACCOUNT_ACTIVATED));
    }

    @Override
    public Response deleteStudentById(String id) {
        studentService.deleteStudentById(id);
        return  new Response(OK,localService.getMessage(DELETE));
    }

    @Override
    public Response updateStudent (String id, StudentUpdate request) {
        studentService.updateStudent(id,request);
        return new Response(OK,localService.getMessage(UPDATE));
    }

    @Override
    public Response getAllStudent(int page, int size) {
        return new Response(OK,studentService.getAllStudent(page,size));
    }

    @Override
    public Response getAllStudentByTraining(Speciality speciality) {
        return new Response(OK,studentService.getAllStudentByTraining(speciality));
    }
}
