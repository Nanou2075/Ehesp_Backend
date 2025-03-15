package com.elearning.elearning.cover;

import com.elearning.elearning.account.Account;
import com.elearning.elearning.module.Module;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public interface ICoverService {
    void uploadCover(MultipartFile file,  Module module) throws IOException;
    void updateCover(MultipartFile file, Module module) throws IOException;
    byte[] getFile(String fileName) throws IOException;
}
