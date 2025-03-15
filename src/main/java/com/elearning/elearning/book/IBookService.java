package com.elearning.elearning.book;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.module.Module;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public interface IBookService {
    void uploadBook (List<MultipartFile> files, Module module) throws IOException;
    void changeBook(String idFile, MultipartFile file);
    byte[] getBook(String id) throws IOException;
    Response getAllByModule();
}
