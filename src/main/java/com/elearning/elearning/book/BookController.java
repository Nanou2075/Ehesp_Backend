package com.elearning.elearning.book;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.FileMessage.FILE_DELETE;
import static com.elearning.elearning.messages.FileMessage.FILE_SUCCESS;


@RestController
@RequiredArgsConstructor
public class BookController implements BookResource {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final LocalService localService;



    @Override
    public Response changeBook(String idFile, MultipartFile book) throws IOException {
        bookService.changeBook(idFile,book);
        return new Response(OK,localService.getMessage(FILE_SUCCESS)) ;
    }

    @Override
    public Response getBookByModuleId (String id) throws IOException {
        return bookService.getAllByModuleId(id);
    }

    @Override
    public Response getBookByModule () throws IOException {
        return bookService.getAllByModule();
    }



    @Override
    public Response  delete (String id) throws IOException {
        bookService.deleteBook(id);
        return new Response(OK,localService.getMessage(FILE_DELETE)) ;
    }

    @Override
    public byte[] getBook(String id) throws IOException {
        return bookService.getBook(id);
    }

    @Override
    public Response  getBookAll () throws IOException {
        return new Response(OK,bookRepository.findAll());

    }




}
