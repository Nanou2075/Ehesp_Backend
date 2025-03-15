package com.elearning.elearning.podcast;

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
public interface IPodcastService {
    void uploadPodcast (List<MultipartFile> files, Module module) throws IOException;
    void changePodcast(String idFile, MultipartFile file) throws IOException;
    byte[] getPodcast(String id) throws IOException;

    Mono<Resource> readPodcast(String id);
}
