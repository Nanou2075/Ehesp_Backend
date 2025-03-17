package com.elearning.elearning.video;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.module.Module;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public interface IVideoService {
    void uploadVideo (List<MultipartFile> files, Module module) throws IOException;
    void changeVideo(String idFile, MultipartFile file) throws IOException;
    Mono<Resource> readVideo(String id) throws IOException;
    Response getAllByModule();

    void  deleteVideo(String id) throws IOException;

    Response getAllByModuleId(String id);
}
