package com.elearning.elearning.module;


import com.elearning.elearning.book.BookRepository;
import com.elearning.elearning.book.BookService;
import com.elearning.elearning.cover.CoverService;
import com.elearning.elearning.exception.AlreadyExistException;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.podcast.PodcastRepository;
import com.elearning.elearning.podcast.PodcastService;
import com.elearning.elearning.security.authentication.AuthenticationService;
import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.video.VideoRepository;
import com.elearning.elearning.video.VideoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.speciality.SpecialityMessage.*;


@Transactional
@RequiredArgsConstructor
@Service
public class ModuleService implements IModuleService {
    private final ModuleRepository moduleRepository;
    private final VideoRepository videoRepository;
    private final PodcastRepository podcastRepository;
    private final BookRepository bookRepository;
    private final LocalService localService;
    private final VideoService videoService;
    private final BookService bookService;
    private final PodcastService podcastService;
    private final CoverService coverService;
    private final AuthenticationService authenticationService;


    /**
     *
     * @param module the value to save in DB
     */

    @Override
    public void addModule(
                          MultipartFile cover,
                          List<MultipartFile> videos,
                          List<MultipartFile> books,
                          List<MultipartFile> podcasts,
                          Module module) throws IOException {
        if (moduleRepository.findByNameIgnoreCase(module.getName()).isPresent()){
           throw new AlreadyExistException(NO,localService.getMessage(TRAINING_EXIT));}
        Module save = moduleRepository.save(module);
        videoService.uploadVideo(videos,module);
        bookService.uploadBook(books,module);
        podcastService.uploadPodcast(podcasts,module);
        coverService.uploadCover(cover,save);
    }


    /**
     *
     * @param module the value to save in DB
     */

    @Override
    public void addModuleFile(List<MultipartFile> files, String type, Module module) throws IOException {
        if (moduleRepository.findById(module.getId()).isEmpty()){
             throw new AlreadyExistException(NO,localService.getMessage(TRAINING_NOT_FOUND));}
       switch (type){
            case "video":
                videoService.uploadVideo(files,module);
                break;
           case "podcast":
               podcastService.uploadPodcast(files,module);
               break;
           case "pdf":
               bookService.uploadBook(files,module);
               break;
        }

    }


    /**
     *
     * @param id permit to get the Module
     * @param module the value to set the current module data
     */

    @Override
    public void  updateModule(String id, Module module) {
         moduleRepository.findById(id).ifPresentOrElse(local ->{
                    module.setName(module.getName());
                    moduleRepository.save(local);},
                 ()-> {throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));});}



    /**
     *
     * @param id permit to get the Module
     * @return  Module
     */
    @Override
    public Module getModule(String id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND)));
    }


    /**
     *
     * @param id  permit to get the Module and delete
     */

    @Override
    public void removeModule(String id) {
        moduleRepository.findById(id).ifPresentOrElse(moduleRepository::delete,()->{
            throw new NotFoundException(NO,localService.getMessage(TRAINING_NOT_FOUND));
        });

    }

    @Override
    public Set<ModuleResponse> getAll() {
        if (moduleRepository.findAll().isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(new HashSet<>(moduleRepository.findAll()))
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }


    @Override
    public Set<ModuleResponse> getAllBySpeciality(Speciality speciality) {
        if (moduleRepository.findAllBySpeciality(speciality).isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(Optional.of(new HashSet<>(moduleRepository.findAll()))
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(TRAINING_NOT_FOUND))));
    }


    @Override
    public Set<ModuleResponse> getAllByStudent() {
        if (moduleRepository.findAllBySpeciality(authenticationService.currentTraining()).isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        return convertToResponse(moduleRepository.findAllBySpeciality(authenticationService.currentTraining()));
    }



    public Set<ModuleResponse> convertToResponse(Set<Module> moduleList) {
        Set<ModuleResponse> moduleResponseList = new HashSet<>();
        moduleList.forEach(module -> {
            moduleResponseList.add(ModuleResponse.builder()
                            .id(module.getId())
                            .speciality(module.getSpeciality())
                            .name(module.getName())
                            .teacher(module.getTeacher())
                            .numberOfVideo(videoRepository.findAllByModule(module).size())
                            .numberOfPdf(bookRepository.findAllByModule(module).size())
                            .numberOfPodcast(podcastRepository.findAllByModule(module).size())
                    .build());
        });
        return moduleResponseList;
    }
}
