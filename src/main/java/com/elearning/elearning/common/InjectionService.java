package com.elearning.elearning.common;

import com.elearning.elearning.account.AccountRepository;
import com.elearning.elearning.admin.AdminRepository;
import com.elearning.elearning.admin.AdminService;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.document.DocumentRepository;
import com.elearning.elearning.document.DocumentService;
import com.elearning.elearning.mail.MailService;
import com.elearning.elearning.security.accessToken.AccessTokenRepository;
import com.elearning.elearning.security.accessToken.AccessTokenService;
import com.elearning.elearning.security.authentication.AuthenticationService;
import com.elearning.elearning.security.configuration.CorsConfiguration;
import com.elearning.elearning.security.configuration.RsaKeyConfiguration;
import com.elearning.elearning.security.refreshToken.RefreshTokenRepository;
import com.elearning.elearning.security.refreshToken.RefreshTokenService;
import com.elearning.elearning.security.token.TokenFilter;
import com.elearning.elearning.security.token.TokenUtils;
import com.elearning.elearning.student.StudentRepository;
import com.elearning.elearning.student.StudentService;
import com.elearning.elearning.verification.VerificationRepository;
import com.elearning.elearning.verification.VerificationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.thymeleaf.TemplateEngine;

@Service
@RequiredArgsConstructor
@Getter
public class InjectionService {
    public final HandlerExceptionResolver handlerExceptionResolver;
    public final DocumentService imageService;
    public final TokenFilter tokenFilter;
    public final CorsConfiguration corsConfiguration;
    public final AccessTokenRepository accessTokenRepository;
    public final LocalService localService;
    public final AdminRepository adminRepository;
    public final PasswordEncoder passwordEncoder;
    public final AccountRepository accountRepository;
    public final ModelMapper modelMapper;
    public final VerificationService verificationService;
    public final DocumentRepository documentRepository;
    public final AuthenticationService service;
    public final JavaMailSender mailSender;
    public final TemplateEngine templateEngine;
    public final AccessTokenService accessTokenService;
    public final RefreshTokenService refreshTokenService;
    public final AuthenticationService userDetailsService;
    public final RsaKeyConfiguration rsaKeyConfiguration;
    public final RefreshTokenRepository refreshTokenRepository;
    public final TokenUtils tokenUtils;
    public final JwtEncoder jwtEncoder;
    public final JwtDecoder jwtDecoder;
    public final StudentRepository studentRepository;
    public final StudentService studentService;
    public final VerificationRepository verificationRepository;
    public final MailService mailService;
    public final AuthenticationService authenticationService;
    public final AdminService adminService;
    public final DocumentService fileService;
}
