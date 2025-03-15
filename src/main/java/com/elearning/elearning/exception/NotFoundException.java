package com.elearning.elearning.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private int status;

    public NotFoundException(int status, String message) {
        super(message);
        this.status = status;
    }

    public NotFoundException(String message) {
        super(message);

    }


}
