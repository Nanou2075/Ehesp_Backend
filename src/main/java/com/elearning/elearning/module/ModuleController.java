package com.elearning.elearning.module;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.speciality.Speciality;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.speciality.SpecialityMessage.*;


@RestController
@RequiredArgsConstructor
public class ModuleController implements ModuleResource {
private final IModuleService moduleService;
private final LocalService localService;

    @Override
    public Response addModule(
                              MultipartFile cover,
                              List<MultipartFile> videos,
                              List<MultipartFile> books,
                              List<MultipartFile> podcasts,
                              Module module) throws IOException {
        moduleService.addModule(cover,videos,books,podcasts,module);
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

    @Override
    public Response getAllModuleByTraining(Speciality speciality) {
        return new Response(OK, moduleService.getAllByTraining(speciality));
    }

    @Override
    public Response getAllModuleByStudent() {
        return new  Response(OK, moduleService.getAllByStudent());
    }
}
