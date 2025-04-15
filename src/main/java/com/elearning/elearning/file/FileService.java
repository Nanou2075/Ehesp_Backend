package com.elearning.elearning.file;


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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.FileMessage.FILE_NOT_FOUND;
import static com.elearning.elearning.speciality.SpecialityMessage.TRAINING_EMPTY;
import static com.elearning.elearning.uitils.FileUtils.FOLDER_VIDEO_PATH;
import static com.elearning.elearning.uitils.FileUtils.URL_VIDEO;


@Transactional
@Service
@RequiredArgsConstructor
public class FileService implements IFileService {
    private final FileRepository fileRepository;
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
    public void uploadFile(List<MultipartFile> files, Module module) throws IOException {
        Path path = Path.of(FOLDER_VIDEO_PATH);
        commService.folderChecking(path);
        files.forEach(file -> {
            String filePath=path+"/"+file.getOriginalFilename();
            File fileSaved = fileRepository.save(File.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .module(module)
                    .filePath(filePath).build());
            fileSaved.setUrl(URL_VIDEO+ fileSaved.getId());
            fileRepository.save(fileSaved);
            try {
                file.transferTo(new java.io.File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }





    @Override
    public void changeFile(String idFile, MultipartFile file) throws IOException {
        Path path = Path.of(FOLDER_VIDEO_PATH);
        commService.folderChecking(path);
        String filePath=path+"/"+file.getOriginalFilename();
        fileRepository.findById(idFile).ifPresentOrElse(video -> {
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
            fileRepository.save(video);

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
        return new java.io.File(FOLDER_VIDEO_PATH +"/"+ filename).delete();
    }

    /**
     *
     * @param file file to send in the folder
     * @param path the path to get file
     */
    public void sendToFolder(MultipartFile file,String path) {
        try {
            file.transferTo(new java.io.File(path));
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
    public byte[] readFile(String id) throws IOException {
        File file = fileRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NO,localService.getMessage(FILE_NOT_FOUND)));
        String filePath=file.getFilePath();
        return Files.readAllBytes(new java.io.File(filePath).toPath());

    }



    @Override
    public Response getAllByModule() {
        Set<File> files = new HashSet<>();
        Set<Module> allModule = moduleRepository.findAllBySpeciality(authenticationService.currentSpeciality());
        if (allModule.isEmpty())
                throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
           allModule.forEach(module -> {
               files.addAll(fileRepository.findAllByModule(module));
           });
        return new Response(OK, convertToResponse(files));
    }

    @Override
    public  void  deleteFile(String id) throws IOException {
        File file = fileRepository.findFileById(id);
        try {
            delete(file.getFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileRepository.delete(file);
    }



    @Override
    public Response getAllByModuleId(String id) {
        Set<File> allByModuleId = fileRepository.findAllByModuleId(id);
        if (allByModuleId.isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        Set<File> files = new HashSet<>(allByModuleId);

        return new Response(OK, convertToResponse(files));
    }

    public Set<FileResponse> convertToResponse(Set<File> fileSet) {
        Set<FileResponse> videosResponseSet = new HashSet<>();
        fileSet.forEach(file -> {
            videosResponseSet.add(FileResponse.builder()
                    .id(file.getId())
                    .module(file.getModule())
                    .fileName(file.getFileName())
                    .url(file.getUrl())
                    .cover(coverRepository.findCoverByModule(file.getModule()).getUrl())
                    .build());
        });
        return videosResponseSet;
    }















}
