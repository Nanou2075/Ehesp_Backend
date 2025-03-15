package com.elearning.elearning.student;

import com.elearning.elearning.common.PageResponse;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
@Service
public interface IStudentService {
    void addStudent(MultipartFile cv,MultipartFile degree,Student student) throws MessagingException, IOException;
    Student getStudentById(String id );
    void updateStudent(String id, StudentUpdate customer);
    void deleteStudentById (String id);
    PageResponse getAllStudent(int page , int size);

}
