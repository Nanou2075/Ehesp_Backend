package com.elearning.elearning.document;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.common.BaseEntity;
import com.elearning.elearning.teacher.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Document extends BaseEntity {
    private String fileName;
    private String filePath;
    private String url;
    private String fileType;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Account account;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Teacher teacher;
}
