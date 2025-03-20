package com.elearning.elearning.module;

import com.elearning.elearning.speciality.Speciality;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public interface IModuleService {
    void addModule(MultipartFile cover
            ,List<MultipartFile> videos,
                   List<MultipartFile> books,
                   List<MultipartFile> podcasts,Module module) throws IOException;

    void addModuleFile(List<MultipartFile> files, String type, Module module) throws IOException;

    void updateModule(String id, Module module);
    Module getModule(String id);
    void removeModule(String id);
    Set<ModuleResponse> getAll();

    Set<ModuleResponse> getAllBySpeciality(Speciality speciality);

    Set<ModuleResponse> getAllByStudent();
}
