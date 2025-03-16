package com.elearning.elearning.video;


import com.elearning.elearning.common.CommService;
import com.elearning.elearning.cover.CoverRepository;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.module.Module;
import com.elearning.elearning.module.ModuleRepository;
import com.elearning.elearning.security.authentication.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.FileMessage.FILE_NOT_FOUND;
import static com.elearning.elearning.training.TrainingMessage.TRAINING_EMPTY;
import static com.elearning.elearning.uitils.FileUtils.*;


@Transactional
@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService {
    private final VideoRepository videoRepository;
    private final LocalService localService;
    private final CommService commService;
    private final AuthenticationService authenticationService;
    private final ModuleRepository moduleRepository;
    private final CoverRepository coverRepository;



    /**
     *
     * @param files the value to upload  in Data
     * @param module the accountId
     * @throws IOException
     */
    @Override
    public void uploadVideo(List<MultipartFile> files, Module module) throws IOException {
        Path path = Path.of(FOLDER_VIDEO_PATH);
        commService.folderChecking(path);
        files.forEach(file -> {
            String filePath=path+"/"+file.getOriginalFilename();
            Video videoSaved = videoRepository.save(Video.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .module(module)
                    .filePath(filePath).build());
            videoSaved.setUrl(URL_VIDEO+videoSaved.getId());
            videoRepository.save(videoSaved);
            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }





    @Override
    public void changeVideo(String idFile, MultipartFile file) throws IOException {
        Path path = Path.of(FOLDER_VIDEO_PATH);
        commService.folderChecking(path);
        String filePath=path+"/"+file.getOriginalFilename();
        videoRepository.findById(idFile).ifPresentOrElse(video -> {
            try {
                delete(video.getFileName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            video.setFileName(file.getOriginalFilename());
            video.setFilePath(filePath);
            video.setFilePath(filePath);
            video.setFileType(file.getContentType());
            video.setModule(video.getModule());
            sendToFolder(file,filePath);
            videoRepository.save(video);

        },() -> {
            throw  new NotFoundException(NO,localService.getMessage(FILE_NOT_FOUND));
        });

    }

    /**
     * @param filename the param to get
     * @return
     * @throws IOException
     */

    public boolean delete (String filename) throws IOException {
        return new File(FOLDER_VIDEO_PATH +"/"+ filename).delete();
    }

    /**
     *
     * @param file file to send in the folder
     * @param path the path to get file
     */
    public void sendToFolder(MultipartFile file,String path) {
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param id the file name to get in database
     * @return
     * @throws IOException
     */

    public Mono<Resource> readVideo(String id) {
        Video file = videoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NO,localService.getMessage(FILE_NOT_FOUND)));
        return Mono.fromSupplier(()->new FileSystemResource(file.getFilePath()));

    }



    @Override
    public Response getAllByModule() {
        Set<Video> videos = new HashSet<>();
        Set<Module> allModule = moduleRepository.findAllByTraining(authenticationService.currentTraining());
        if (allModule.isEmpty())
                throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
           allModule.forEach(module -> {
               videos.addAll(videoRepository.findAllByModule(module));
           });
        return new Response(OK, convertToResponse(videos));
    }



    @Override
    public Response getAllByModuleId(String id) {
        Set<Video> allByModuleId = videoRepository.findAllByModuleId(id);
        if (allByModuleId.isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        Set<Video> videos = new HashSet<>(allByModuleId);

        return new Response(OK, convertToResponse(videos));
    }

    public Set<VideoResponse> convertToResponse(Set<Video> videoSet) {
        Set<VideoResponse> videosResponseSet = new HashSet<>();
        videoSet.forEach(video -> {
            videosResponseSet.add(VideoResponse.builder()
                    .id(video.getId())
                    .module(video.getModule())
                    .fileName(video.getFileName())
                    .url(video.getUrl())
                    .numberOfVideo(videoRepository.findAllByModule(video.getModule()).size())
                    .cover(coverRepository.findCoverByModule(video.getModule()).getUrl())
                    .build());
        });
        return videosResponseSet;
    }















}
