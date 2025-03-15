package com.elearning.elearning.degree;

import com.elearning.elearning.account.Account;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public interface IDegreeService {
    void updateCV(MultipartFile file) throws IOException;

    void uploadCV(MultipartFile file, Account account) throws IOException;

    byte[] getFile(String fileName) throws IOException;
}
