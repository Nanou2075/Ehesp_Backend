package com.elearning.elearning.module;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.speciality.Speciality;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("module/")
public interface ModuleResource {
    @PostMapping(value = "save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response addModule( @RequestParam(value = "cover",required = false)   MultipartFile cover,
            @RequestParam(value = "videos",required = false) List<MultipartFile> videos,
                       @RequestParam(value = "books",required = false) List<MultipartFile> books,
                       @RequestParam(value = "podcasts",required = false) List<MultipartFile> podcasts,

                       @RequestPart Module module ) throws IOException;

    @PostMapping(value = "addFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response addModuleFile (@RequestParam(value = "files",required = false) List<MultipartFile> files,
                       @RequestPart(value = "type",required = false)String type,
                       @RequestPart Module module) throws IOException;


    @PutMapping("{id}")
    Response updateModule(@PathVariable String id ,@RequestBody Module module);

    @DeleteMapping("{id}")
    Response removeModule(@PathVariable String id );

    @GetMapping("{id}")
    Response getModule(@PathVariable String id );

    @GetMapping("all")
    Response getAllModule();

    @PostMapping("speciality")
    Response getAllModuleBySpeciality(@RequestBody Speciality speciality);

    @GetMapping("student")
    Response getAllModuleByStudent();
}
