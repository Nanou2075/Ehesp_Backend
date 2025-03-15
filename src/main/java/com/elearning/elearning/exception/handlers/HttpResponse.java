package com.elearning.elearning.exception.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse {

    private int status;
    private String message;
    private Object errors;

    public HttpResponse(int status, String message, Object errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpResponse(int status, Object errors) {
        this.status = status;
        this.errors = errors;
    }
}
