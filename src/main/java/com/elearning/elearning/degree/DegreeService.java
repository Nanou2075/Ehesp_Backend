package com.elearning.elearning.degree;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.common.CommService;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
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
public class DegreeService implements IDegreeService {
    private final DegreeRepository degreeRepository;
    private final CommService commService;
    private final LocalService localService;
    private final AuthenticationService service;



    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    @Override
    public void updateMyDegree (MultipartFile file) throws IOException {
        loadFile(file,service.currentAccount());
    }


    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    @Override
    public void uploadDegree (MultipartFile file, Account account) throws IOException {
        loadFile(file,account);


    }


    /**
     *
     * @param id the file name to get in database
     * @return
     * @throws IOException
     */
    @Override
     public byte[] getFile(String id) throws IOException {
        Degree file = degreeRepository.findById(id).orElseThrow(
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
     return new File(FOLDER_DOCUMENT_PATH +"/"+ filename).delete();
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




    public void  loadFile (MultipartFile file, Account account) throws IOException {
        Path path = Path.of(FOLDER_BOOK_PATH);
        commService.folderChecking(path);
        String filePath=path+file.getOriginalFilename();
        degreeRepository.findByAccount(account).ifPresentOrElse(degree -> {
                    try {
                        delete(degree.getFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    degree.setFileName(file.getOriginalFilename());
                    degree.setFilePath(filePath);
                    degree.setFilePath(filePath);
                    degree.setFileType(file.getContentType());
                    sendToFolder(file,filePath);
                    degreeRepository.save(degree);

                },() -> {
                    Degree fileSaved = degreeRepository.save(Degree.builder()
                            .fileName(file.getOriginalFilename())
                            .fileType(file.getContentType())
                            .account(account)
                            .filePath(filePath).build());
                    sendToFolder(file,filePath);
                    fileSaved.setUrl(URL_DOCUMENT+fileSaved.getId());
                    degreeRepository.save(fileSaved);
                }
        );
    }






}
