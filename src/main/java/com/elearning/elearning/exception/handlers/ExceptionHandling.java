package com.elearning.elearning.exception.handlers;


import com.elearning.elearning.exception.AlreadyExistException;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.Security.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionHandling implements ErrorController {
    private final LocalService localService;



    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(FORBIDDEN, localService.getMessage(ACCOUNT_DISABLED));
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<HttpResponse> ExpiredJwtException() {
        return createHttpResponse(NOT_FOUND, localService.getMessage(REFRESH_EXPIRED));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponses(BAD_REQUEST, localService.getMessage(INCORRECT_CREDENTIALS));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponses(FORBIDDEN, localService.getMessage(ACCESS_DENIED_MESSAGE));
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<HttpResponse> MessagingException() {
        return createHttpResponses(FORBIDDEN, localService.getMessage(EMAIL_SERVER_ERROR_MSG));
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, localService.getMessage(ACCOUNT_LOCKED));
    }


    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<HttpResponse> SignatureException() {
        return createHttpResponse(UNAUTHORIZED, localService.getMessage(ACCOUNT_LOCKED));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
        return createHttpResponses(BAD_REQUEST, localService.getMessage(URL));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponses(METHOD_NOT_ALLOWED, String.format(localService.getMessage(METHOD_IS_NOT_ALLOWED), supportedMethod));
    }


    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        log.error(exception.getMessage());
        return createHttpResponses(NOT_FOUND, localService.getMessage(EMAIL_SERVER_ERROR_MSG));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        log.error(exception.getMessage());
        return createHttpResponses(INTERNAL_SERVER_ERROR, localService.getMessage(ERROR_PROCESSING_FILE));
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), message), httpStatus);
    }


    private ResponseEntity<HttpResponse> createHttpResponses(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(NO, message), httpStatus);
    }

    private ResponseEntity<HttpResponse> createHttpResponses(Object error) {
        return new ResponseEntity<>(new HttpResponse(NO, error), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> EntityNotFoundException(NotFoundException exception) {
        final HttpResponse httpResponse = HttpResponse.builder()
                .status(exception.getStatus())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<HttpResponse> AlreadyExistException(AlreadyExistException exception) {
        final HttpResponse httpResponse = HttpResponse.builder()
                .status(exception.getStatus())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    String fieldName = error.getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return createHttpResponses(errors);
    }


    public String getErrorPath() {
        return localService.getMessage(ERROR_PATH);
    }


}
