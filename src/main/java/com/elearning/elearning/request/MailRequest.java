package com.elearning.elearning.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailRequest {
    private String destinationMail;
    private String fullName;
    private String code;
}
