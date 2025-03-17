package com.elearning.elearning.podcast;


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
public class PodcastController implements PodcastResource {
    private final PodcastService podcastService;
    private final PodcastRepository podcastRepository;
    private final LocalService localService;



    @Override
    public Response change(String idFile, MultipartFile file) throws IOException {
        podcastService.changePodcast(idFile,file);
        return new Response(OK,localService.getMessage(FILE_SUCCESS)) ;
    }

    @Override
    public Response getPodcastByModuleId(String id) throws IOException {
        return podcastService.getAllByModuleId(id);
    }

    @Override
    public Response getPodcastByModule () throws IOException {
        return podcastService.getAllByModule();
    }





    @Override
    public Mono<Resource> readPodcast(String id) throws IOException {
        return podcastService.readPodcast(id);
    }



    @Override
    public Response  getPodcastAll () throws IOException {
        return new Response(OK,podcastRepository.findAll());

    }

    @Override
    public Response  delete (String id) throws IOException {
        podcastService.deletePodcast(id);
        return new Response(OK,localService.getMessage(FILE_DELETE)) ;
    }


}
