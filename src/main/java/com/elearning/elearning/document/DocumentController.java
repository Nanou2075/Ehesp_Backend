package com.elearning.elearning.document;


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
public class DocumentController implements DocumentResource {
    private final DocumentService documentService;
    private final LocalService localService;
    @Override
    public byte[]  getFile (String id) throws IOException {
        return documentService.getFile(id);
    }


    @Override
    public Response updateCV(MultipartFile file) throws IOException {
        documentService.updateCV(file);
        return new Response(OK,localService.getMessage(FILE_SUCCESS));

    }






}
