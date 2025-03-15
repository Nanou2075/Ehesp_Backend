package com.elearning.elearning.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class CommService {
    public void   folderChecking (Path path) throws IOException {
        if (!Files.exists(path))
            Files.createDirectories(path);
    }



}
