package com.elearning.elearning.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static com.elearning.elearning.messages.Format.FORMAT_CODE;

@Service
@Transactional
public class CommService {
    public void   folderChecking (Path path) throws IOException {
        if (!Files.exists(path))
            Files.createDirectories(path);
    }


    public String password() {
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        return String.format(FORMAT_CODE, randomInteger);
    }



}
