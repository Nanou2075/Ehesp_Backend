package com.elearning.elearning.verification;



import com.elearning.elearning.account.Account;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.mail.MailService;
import com.elearning.elearning.request.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.AccountMessage.ACCOUNT_ACTIVATED;
import static com.elearning.elearning.messages.AccountMessage.CODE_NO_VALID;
import static com.elearning.elearning.messages.Format.*;


@Transactional
@Service
@RequiredArgsConstructor
public class VerificationService {
    private final VerificationRepository verificationRepository;
    private final LocalService localService;
    private final MailService mailService;

    /**
     *
     * @return the expiration time
     */
    public Instant expirationDate() {
        Instant creationDate = Instant.now();
        return creationDate.plus(VALIDATION, ChronoUnit.MINUTES);

    }

    /**
     *
     * @return the code generated
     */
    public String activationCode() {
        Random random = new Random();
        int randomInteger = random.nextInt(VALUE);
        return String.format(FORMAT_CODE, randomInteger);
    }

    /**
     *
     * @param account permit to get account information verification code sending
     */

    public void verificationCode(Account account,String password) throws MessagingException {
       Verification verification = Verification.builder()
                .code(activationCode())
                .expiredDate(expirationDate())
                .createdDate(Instant.now())
                .generate(true)
                .account(account)
                .build();
        verificationRepository.save(verification);
        MailRequest mailRequest = MailRequest.builder().
                code(verification.getCode())
                .password(password)
                .destinationMail(account.getMail())
                .username(account.getMail()).
                build();
        mailService.sendVerificationMail(mailRequest);

    }

    /**
     *
     * @param request permit to send code for cheeking
     */

    public void verification (VerificationRequest request) {
        verificationRepository.findByCode(request.getCode())
                .ifPresentOrElse(verification -> {
                                  if (verification.getExpiredDate().isBefore(Instant.now()) || verification.isCheeked())
                                      throw new NotFoundException(NO,localService.getMessage(CODE_NO_VALID));
                                  verification.setActivatedDate(Instant.now());
                                  verification.setCheeked(true);
                                  verification.getAccount().setActivated(true);
                            verification.getAccount().setNotLocked(true);

                            verificationRepository.save(verification);
              verification.setActivatedDate(Instant.now());
             verificationRepository.save(verification);},
                        ()-> {throw new NotFoundException(NO,localService.getMessage(ACCOUNT_ACTIVATED));
                });
    }



}






