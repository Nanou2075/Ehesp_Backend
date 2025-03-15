package com.elearning.elearning.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailTemplate {
    VERIFICATION_CONFIRMATION("verification-confirmation","Validation successfully processed");
    private final String template;
    private final String subject;

}
