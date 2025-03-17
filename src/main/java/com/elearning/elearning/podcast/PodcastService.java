package com.elearning.elearning.podcast;


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
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
public class PodcastService implements IPodcastService {
    private final PodcastRepository podcastRepository;
    private final LocalService localService;
    private final CommService  commService;
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
    public void uploadPodcast(List<MultipartFile> files, Module module) throws IOException {
        Path path = Path.of(FOLDER_PODCAST_PATH);
        commService.folderChecking(path);
        files.forEach(file -> {
            String filePath=path+"/"+file.getOriginalFilename();
            Podcast podcastSaved = podcastRepository.save(Podcast.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .module(module)
                    .filePath(filePath).build());
            podcastSaved.setUrl(URL_PODCAST+ podcastSaved.getId());
            podcastRepository.save(podcastSaved);
            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }



    @Override
    public Response getAllByModuleId(String id) {
        Set<Podcast> allByModuleId = podcastRepository.findAllByModuleId(id);
        if (allByModuleId.isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        Set<Podcast> podcasts= new HashSet<>(allByModuleId);

        return new Response(OK, convertToResponse(podcasts));
    }

    @Override
    public  void  deletePodcast(String id) throws IOException {
        Podcast podcast = podcastRepository.findPodcastById(id);
        delete(podcast.getFileName());
        podcastRepository.delete(podcast);
    }




    @Override
    public void changePodcast(String idFile, MultipartFile file) throws IOException {
        Path path = Path.of(FOLDER_PODCAST_PATH);
        commService.folderChecking(path);
        String filePath=path+file.getOriginalFilename();
        podcastRepository.findById(idFile).ifPresentOrElse(podcast -> {
            try {
                delete(podcast.getFileName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            podcast.setFileName(file.getOriginalFilename());
            podcast.setFilePath(filePath);
            podcast.setFilePath(filePath);
            podcast.setFileType(file.getContentType());
            podcast.setModule(podcast.getModule());
            sendToFolder(file,filePath);
            podcastRepository.save(podcast);

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
        return new File(FOLDER_PODCAST_PATH+"/"+ filename).delete();
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
    @Override
    public Mono<Resource> readPodcast(String id) {
        Podcast file = podcastRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NO,localService.getMessage(FILE_NOT_FOUND)));
        return Mono.fromSupplier(()->new FileSystemResource(file.getFilePath()));

    }

    @Override
    public Response getAllByModule() {
        Set<Podcast> podcasts = new HashSet<>();
        Set<Module> allModule = moduleRepository.findAllByTraining(authenticationService.currentTraining());
        if (allModule.isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        allModule.forEach(module -> {
            podcasts.addAll(podcastRepository.findAllByModule(module));
        });
        return new Response(OK,convertToResponse(podcasts));
    }

    public Set<PodcastResponse> convertToResponse(Set<Podcast> podcastSet) {
        Set<PodcastResponse> podcastResponseSet = new HashSet<>();
        podcastSet.forEach(podcast-> {
            podcastResponseSet.add(PodcastResponse.builder()
                    .id(podcast.getId())
                    .module(podcast.getModule())
                    .fileName(podcast.getFileName())
                    .url(podcast.getUrl())
                    .numberOfPodcast(podcastRepository.findAllByModule(podcast.getModule()).size())
                    .cover(coverRepository.findCoverByModule(podcast.getModule()).getUrl())
                    .build());
        });
        return podcastResponseSet;
    }





}
