package com.elearning.elearning.file;


import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.module.Module;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class File extends BaseEntity {
    private String fileName;
    private String filePath;
    private String url;
    private String fileType;
    @ManyToOne
    private Module module;
}

