package com.elearning.elearning.exception.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Setter
@Getter
@Service
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private Object data;


    public Response(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;

    }


}
