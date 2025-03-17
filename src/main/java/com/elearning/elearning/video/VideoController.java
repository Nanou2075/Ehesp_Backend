package com.elearning.elearning.video;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.FileMessage.FILE_DELETE;
import static com.elearning.elearning.messages.FileMessage.FILE_SUCCESS;


@RestController
@RequiredArgsConstructor
public class VideoController implements VideoResource {
    private final VideoService videoService;
    private final LocalService localService;
    private final VideoRepository videoRepository;



    @Override
    public Response change(String idFile, MultipartFile file) throws IOException {
        videoService.changeVideo(idFile,file);
        return new Response(OK,localService.getMessage(FILE_SUCCESS)) ;
    }

    @Override
    public Response getVideoByModule () throws IOException {
        return videoService.getAllByModule();
    }

    @Override
    public Response getVideoByModuleId (String id) throws IOException {
        return videoService.getAllByModuleId(id);
    }



    @Override
    public Mono<Resource> readVideo(String id) throws IOException {
        return videoService.readVideo(id);

    }


    @Override
    public Response  getVideoAll () throws IOException {
        return new Response(OK,videoRepository.findAll());

    }

    @Override
    public Response  delete (String id) throws IOException {
        videoService.deleteVideo(id);
        return new Response(OK,localService.getMessage(FILE_DELETE)) ;
    }





}
