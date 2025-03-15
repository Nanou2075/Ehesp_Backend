package com.elearning.elearning.document;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.common.CommService;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.security.authentication.AuthenticationService;
import com.elearning.elearning.teacher.Teacher;
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
public class DocumentService implements IDocumentService {
    private final DocumentRepository documentRepository;
    private final CommService commService;
    private final LocalService localService;
    private final AuthenticationService service;



    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    @Override
    public void updateMyCV (MultipartFile file) throws IOException {
        loadFile(file,service.currentAccount());
    }

    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    @Override
    public void uploadTeacherCV(MultipartFile file, Teacher teacher) throws IOException {
        loadTeacherFile(file,teacher);


    }


    /**
     *
     * @param file the value to upload  in Data
     * @throws IOException
     */
    @Override
    public void uploadCV(MultipartFile file, Account account) throws IOException {
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
        Document file = documentRepository.findById(id).orElseThrow(
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



    public void  loadTeacherFile (MultipartFile file, Teacher teacher) throws IOException {
        Path path = Path.of(FOLDER_BOOK_PATH);
        commService.folderChecking(path);
        String filePath=path+file.getOriginalFilename();
        documentRepository.findByTeacher(teacher).ifPresentOrElse(document -> {
                    try {
                        delete(document.getFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    document.setFileName(file.getOriginalFilename());
                    document.setFilePath(filePath);
                    document.setFilePath(filePath);
                    document.setFileType(file.getContentType());
                    sendToFolder(file,filePath);
                    documentRepository.save(document);

                },() -> {
                    Document fileSaved = documentRepository.save(Document.builder()
                            .fileName(file.getOriginalFilename())
                            .fileType(file.getContentType())
                            .teacher( teacher)
                            .filePath(filePath).build());
                    sendToFolder(file,filePath);
                    fileSaved.setUrl(URL_DOCUMENT+fileSaved.getId());
                    documentRepository.save(fileSaved);
                }
        );
    }




    public void  loadFile (MultipartFile file, Account account) throws IOException {
        Path path = Path.of(FOLDER_BOOK_PATH);
        commService.folderChecking(path);
        String filePath=path+file.getOriginalFilename();
        documentRepository.findByAccount(account).ifPresentOrElse(document -> {
                    try {
                        delete(document.getFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    document.setFileName(file.getOriginalFilename());
                    document.setFilePath(filePath);
                    document.setFilePath(filePath);
                    document.setFileType(file.getContentType());
                    sendToFolder(file,filePath);
                    documentRepository.save(document);

                },() -> {
                    Document fileSaved = documentRepository.save(Document.builder()
                            .fileName(file.getOriginalFilename())
                            .fileType(file.getContentType())
                            .account( service.currentAccount())
                            .filePath(filePath).build());
                    sendToFolder(file,filePath);
                    fileSaved.setUrl(URL_DOCUMENT+fileSaved.getId());
                    documentRepository.save(fileSaved);
                }
        );
    }






}
