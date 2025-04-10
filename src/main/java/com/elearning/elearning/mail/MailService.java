package com.elearning.elearning.mail;

import com.elearning.elearning.request.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.util.HashMap;
import java.util.Map;


import static com.elearning.elearning.mail.MailTemplate.REGISTRATION_CONFIRMATION;
import static com.elearning.elearning.mail.MailTemplate.VALIDATION_CONFIRMATION;
import static com.elearning.elearning.messages.MailValue.username;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;



    @Async
    public void sendVerificationMail(MailRequest request) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(
                mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                , UTF_8.name());
        mimeMessageHelper.setFrom(username);
        mimeMessageHelper.setTo(request.getDestinationMail());
        final String templateName = VALIDATION_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("password", request.getPassword());
        variables.put("username", request.getUsername());
        variables.put("code", request.getCode());
        Context context = new Context();

        context.setVariables(variables);
        mimeMessageHelper.setSubject(VALIDATION_CONFIRMATION.getSubject());
        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(request.getDestinationMail());
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully send to %s with template %s", request.getDestinationMail(), templateName));
        } catch (MessagingException e) {
            log.warn("WARNING - Email send to %s failed", request.getDestinationMail());
        }
    }



        @Async
        public void sendRegisterMail (String email) throws MessagingException {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper
                    = new MimeMessageHelper(
                    mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED
                    , UTF_8.name());
            mimeMessageHelper.setFrom(username);
            mimeMessageHelper.setTo(email);
            final String templateName = REGISTRATION_CONFIRMATION.getTemplate();
            mimeMessageHelper.setSubject(REGISTRATION_CONFIRMATION.getSubject());
            try {
                Context context = new Context();
                String htmlTemplate = templateEngine.process(templateName, context);
                mimeMessageHelper.setText(htmlTemplate, true);
                mimeMessageHelper.setTo(email);
                mailSender.send(mimeMessage);
                log.info(String.format("INFO - Email successfully send to %s with template %s",email, templateName));
            } catch (MessagingException e) {
                log.warn("WARNING - Email send to %s failed", email);
            }


        }
    }






