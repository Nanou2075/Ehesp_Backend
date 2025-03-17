package com.elearning.elearning.podcast;

import com.elearning.elearning.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PodcastRepository extends JpaRepository<Podcast,String> {
    Set<Podcast> findAllByModule(Module module);
    Set<Podcast> findAllByModuleId(String id);
    Podcast findPodcastById(String id);
}
