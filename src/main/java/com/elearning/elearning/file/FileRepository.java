package com.elearning.elearning.file;

import com.elearning.elearning.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FileRepository extends JpaRepository<File,String> {
    Set<File> findAllByModule(Module module);
    Set<File> findAllByModuleId(String moduleId);
    File findFileById(String id);
}
