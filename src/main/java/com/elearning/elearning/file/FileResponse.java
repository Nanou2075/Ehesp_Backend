package com.elearning.elearning.file;

import com.elearning.elearning.module.Module;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponse {
    private String id;
    private String fileName;
    private String filePath;
    private String url;
    private String fileType;
    private Module module;
    private String cover;
    private int numberOfVideo;

}
