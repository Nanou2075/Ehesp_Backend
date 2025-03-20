package com.elearning.elearning.file;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.module.Module;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public interface IFileService {
    void uploadFile (List<MultipartFile> files, Module module) throws IOException;
    void changeFile(String idFile, MultipartFile file) throws IOException;
    byte[] readFile(String id) throws IOException;
    Response getAllByModule();

    void  deleteFile(String id) throws IOException;

    Response getAllByModuleId(String id);
}
