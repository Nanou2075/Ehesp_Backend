package com.elearning.elearning.degree;


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
public class DegreeController implements DegreeResource {
    private final DegreeService documentService;
    private final LocalService localService;

    @Override
    public byte[]  getFile (String id) throws IOException {
        return documentService.getFile(id);
    }


    @Override
    public Response updateMyDegree(MultipartFile file) throws IOException {
        documentService.updateMyDegree(file);
        return new Response(OK,localService.getMessage(FILE_SUCCESS));

    }






}
