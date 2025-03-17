package com.elearning.elearning.podcast;

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
public interface IPodcastService {
    void uploadPodcast (List<MultipartFile> files, Module module) throws IOException;

    Response getAllByModuleId(String id);

    void  deletePodcast(String id) throws IOException;

    void changePodcast(String idFile, MultipartFile file) throws IOException;
    Mono<Resource> readPodcast(String id);
    Response getAllByModule();
}
