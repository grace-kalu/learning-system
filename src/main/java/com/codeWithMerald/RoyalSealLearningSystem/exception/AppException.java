package com.codeWithMerald.RoyalSealLearningSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AppException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public AppException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
