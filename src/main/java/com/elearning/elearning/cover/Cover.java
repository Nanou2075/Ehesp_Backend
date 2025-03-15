package com.elearning.elearning.cover;


import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.module.Module;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Cover extends BaseEntity {
    private String fileName;
    private String filePath;
    private String url;
    private String fileType;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Module module;
}
