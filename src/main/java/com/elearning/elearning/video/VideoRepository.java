package com.elearning.elearning.video;

import com.elearning.elearning.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface VideoRepository extends JpaRepository<Video,String> {
    Set<Video> findAllByModule(Module module);
}
