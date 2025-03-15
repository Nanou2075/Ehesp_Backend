package com.elearning.elearning.cover;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.module.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.FileMessage.FILE_SUCCESS;


@RestController
@RequiredArgsConstructor
public class CoverController implements CoverResource {
    private final CoverService documentService;
    private final LocalService localService;
    @Override
    public byte[]  getFile (String id) throws IOException {
        return documentService.getFile(id);
    }


    @Override
    public Response updateCover(MultipartFile file, Module module) throws IOException {
        documentService.updateCover(file,module);
        return new Response(OK,localService.getMessage(FILE_SUCCESS));

    }






}
