package com.elearning.elearning.mail;

import jakarta.servlet.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailTemplate {
    VERIFICATION_CONFIRMATION("verification-confirmation","Validation successfully processed"),
    REGISTRATION_CONFIRMATION("creation-email","Validation d'inscription "),
    VALIDATION_CONFIRMATION("validation-email","Validation d'inscription ");


    private final String template;
    private final String subject;

}
