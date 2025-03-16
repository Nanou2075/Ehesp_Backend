package com.elearning.elearning.student;

import com.elearning.elearning.common.PageResponse;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.training.Training;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public interface IStudentService {
    void addStudent(MultipartFile cv,MultipartFile degree,Student student) throws MessagingException, IOException;
    Student getStudentById(String id );

    Student validStudentBy(String id) throws MessagingException, IOException;

    void updateStudent(String id, StudentUpdate customer);
    void deleteStudentById (String id);
    PageResponse getAllStudent(int page , int size);

    Set<StudentResponse> getAllStudentByTraining(Training training);
}
