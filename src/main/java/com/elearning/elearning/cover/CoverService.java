package com.elearning.elearning.cover;


import com.elearning.elearning.common.CommService;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.module.Module;
import com.elearning.elearning.security.authentication.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.FileMessage.FILE_NOT_FOUND;
import static com.elearning.elearning.uitils.FileUtils.*;


@Transactional
@Service
@RequiredArgsConstructor
public class CoverService implements ICoverService {
    private final CoverRepository coverRepository;
    private final CommService commService;
    private final LocalService localService;
    private final AuthenticationService service;



    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    @Override
    public void updateCover(MultipartFile file,Module module) throws IOException {
        loadFile(file,module);
    }

    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    public void uploadCover(MultipartFile file, Module module) throws IOException {
        loadFile(file,module);


    }


    /**
     *
     * @param id the file name to get in database
     * @return
     * @throws IOException
     */
    @Override
     public byte[] getFile(String id) throws IOException {
        Cover file = coverRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NO,localService.getMessage(FILE_NOT_FOUND)));
        String filePath=file.getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());

    }


    /**
     * @param filename the param to get
     * @return
     * @throws IOException
     */

    public boolean delete (String filename) throws IOException {
     return new File(FOLDER_COVER_PATH +"/"+ filename).delete();
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




    public void  loadFile (MultipartFile file, Module module) throws IOException {
        Path path = Path.of(FOLDER_COVER_PATH);
        commService.folderChecking(path);
        String filePath=path+"/"+file.getOriginalFilename();
        coverRepository.findByModule(module).ifPresentOrElse(cover -> {
                    try {
                        delete(cover.getFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    cover.setFileName(file.getOriginalFilename());
                    cover.setFilePath(filePath);
                    cover.setFilePath(filePath);
                    cover.setModule(module);
                    cover.setFileType(file.getContentType());
                    sendToFolder(file,filePath);
                    coverRepository.save(cover);

                },() -> {
                    Cover fileSaved = coverRepository.save(Cover.builder()
                            .fileName(file.getOriginalFilename())
                            .fileType(file.getContentType())
                            .module( module)
                            .filePath(filePath).build());
                    sendToFolder(file,filePath);
                    fileSaved.setUrl(URL_COVER+fileSaved.getId());
                    coverRepository.save(fileSaved);
                }
        );
    }






}
