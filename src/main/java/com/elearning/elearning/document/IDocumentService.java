package com.elearning.elearning.document;

import com.elearning.elearning.account.Account;
import com.elearning.elearning.teacher.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public interface IDocumentService {
    void updateMyCV(MultipartFile file) throws IOException;

    void uploadTeacherCV(MultipartFile file, Teacher teacher) throws IOException;

    void uploadCV(MultipartFile file, Account account) throws IOException;

    byte[] getFile(String fileName) throws IOException;
}
