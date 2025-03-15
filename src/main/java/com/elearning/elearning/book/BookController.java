package com.elearning.elearning.book;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.FileMessage.FILE_SUCCESS;


@RestController
@RequiredArgsConstructor
public class BookController implements BookResource {
    private final BookService fileService;
    private final LocalService localService;



    @Override
    public Response changeBook(String idFile, MultipartFile file) throws IOException {
        fileService.changeBook(idFile,file);
        return new Response(OK,localService.getMessage(FILE_SUCCESS)) ;
    }




    @Override
    public byte[] getBook(String id) throws IOException {
        return fileService.getBook(id);
    }




}
