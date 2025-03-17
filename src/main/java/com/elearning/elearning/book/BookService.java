package com.elearning.elearning.book;


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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
public class BookService implements IBookService {
    private final BookRepository bookRepository;
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
    public void uploadBook(List<MultipartFile> files, Module module) throws IOException {
        Path path = Path.of(FOLDER_BOOK_PATH);
        commService.folderChecking(path);
        files.forEach(file -> {
            String filePath=path+"/"+file.getOriginalFilename();
            Book bookSaved = bookRepository.save(Book.builder()
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .module(module)
                    .filePath(filePath).build());
            bookSaved.setUrl(URL_BOOK+ bookSaved.getId());
            bookRepository.save(bookSaved);
            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    @Override
    public  void  deleteBook(String id) throws IOException {
       Book book = bookRepository.findBookById(id);
        try {
            delete(book.getFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookRepository.delete(book);
    }




    @Override
    public void changeBook(String idFile, MultipartFile file) {
        String filePath=FOLDER_BOOK_PATH+file.getOriginalFilename();
        bookRepository.findById(idFile).ifPresentOrElse(book -> {
            try {
                delete(book.getFileName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            book.setFileName(file.getOriginalFilename());
            book.setFilePath(filePath);
            book.setFilePath(filePath);
            book.setFileType(file.getContentType());
            book.setModule(book.getModule());
            sendToFolder(file,filePath);
            bookRepository.save(book);

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
        return new File(FOLDER_BOOK_PATH + filename).delete();
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
    public byte[] getBook(String id) throws IOException {
        Book file = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NO,localService.getMessage(FILE_NOT_FOUND)));
        String filePath=file.getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());

    }


    @Override
    public Response getAllByModuleId(String id) {
        Set<Book> allByModuleId = bookRepository.findAllByModuleId(id);
        if (allByModuleId.isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        Set<Book> books = new HashSet<>(allByModuleId);

        return new Response(OK, convertToResponse(books));
    }

    @Override
    public Response getAllByModule() {
        Set<Book> modules = new HashSet<>();
        Set<Module> allModule = moduleRepository.findAllByTraining(authenticationService.currentTraining());
        if (allModule.isEmpty())
            throw new NotFoundException(NO,localService.getMessage(TRAINING_EMPTY));
        allModule.forEach(module -> {
            modules.addAll(bookRepository.findAllByModule(module));
        });
        return new Response(OK, convertToResponse(modules));
    }




    public Set<BookResponse> convertToResponse(Set<Book> bookSet) {
        Set<BookResponse> bookResponseSet = new HashSet<>();
        bookSet.forEach(book-> {
            bookResponseSet.add(BookResponse.builder()
                    .id(book.getId())
                    .module(book.getModule())
                    .fileName(book.getFileName())
                    .url(book.getUrl())
                    .numberOfBook(bookRepository.findAllByModule(book.getModule()).size())
                    .cover(coverRepository.findCoverByModule(book.getModule()).getUrl())
                    .build());
        });
        return bookResponseSet;
    }








}
