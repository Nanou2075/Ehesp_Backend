package com.elearning.elearning.cover;

import com.elearning.elearning.module.Module;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ICoverResponse {
    private String id;
    private String fileName;
    private String filePath;
    private String url;
    private String fileType;
    private Module module;

}
