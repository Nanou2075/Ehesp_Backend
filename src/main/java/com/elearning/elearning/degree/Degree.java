package com.elearning.elearning.degree;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.common.BaseEntity;
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
public class Degree extends BaseEntity {
    private String fileName;
    private String filePath;
    private String url;
    private String fileType;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Account account;
}
