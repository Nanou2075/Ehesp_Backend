package com.elearning.elearning.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistException extends RuntimeException {
    private int status;

    public AlreadyExistException(int status, String message) {
        super(message);
        this.status = status;
    }

    public AlreadyExistException(String message) {
        super(message);

    }


}
