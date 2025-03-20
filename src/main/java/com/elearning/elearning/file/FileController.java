package com.elearning.elearning.file;


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
public class FileController implements FileResource {
    private final FileService fileService;
    private final LocalService localService;
    private final FileRepository fileRepository;



    @Override
    public Response change(String idFile, MultipartFile file) throws IOException {
        fileService.changeFile(idFile,file);
        return new Response(OK,localService.getMessage(FILE_SUCCESS)) ;
    }

    @Override
    public Response getFileByModule () throws IOException {
        return fileService.getAllByModule();
    }

    @Override
    public Response getFileByModuleId (String id) throws IOException {
        return fileService.getAllByModuleId(id);
    }



    @Override
    public byte[] readFile(String id) throws IOException {
        return fileService.readFile(id);

    }

    


    @Override
    public Response  getFileAll () throws IOException {
        return new Response(OK, fileRepository.findAll());

    }

    @Override
    public Response  delete (String id) throws IOException {
        fileService.deleteFile(id);
        return new Response(OK,localService.getMessage(FILE_DELETE)) ;
    }





}
