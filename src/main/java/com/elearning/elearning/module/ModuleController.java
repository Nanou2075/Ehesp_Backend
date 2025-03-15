package com.elearning.elearning.module;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.training.TrainingMessage.*;


@RestController
@RequiredArgsConstructor
public class ModuleController implements ModuleResource {
private final IModuleService moduleService;
private final LocalService localService;

    @Override
    public Response addModule(List<MultipartFile> videos,
                              List<MultipartFile> books,
                              List<MultipartFile> podcasts,
                              MultipartFile cover,
                              Module module) throws IOException {
        moduleService.addModule(videos,books,podcasts,cover,module);
        return new Response(OK,localService.getMessage(TRAINING_SUCCESS));
    }

    @Override
    public Response addModuleFile(List<MultipartFile> files,
                                  String type,
                                  Module module) throws IOException {
        moduleService.addModuleFile(files,type,module);
        return new Response(OK,localService.getMessage(TRAINING_SUCCESS));
    }

    @Override
    public Response updateModule(String id, Module module) {
        moduleService.updateModule(id, module);
        return new Response(OK,localService.getMessage(TRAINING_UPDATE));
    }

    @Override
    public Response removeModule(String id) {
        return new Response(OK, moduleService.getModule(id));
    }

    @Override
    public Response getModule(String id) {
        moduleService.removeModule(id);
        return new Response(OK,localService.getMessage(TRAINING_DELETE));
    }

    @Override
    public Response getAllModule() {
        return new Response(OK, moduleService.getAll());
    }
}
