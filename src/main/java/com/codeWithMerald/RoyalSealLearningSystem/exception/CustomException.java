package com.codeWithMerald.RoyalSealLearningSystem.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Throwable {
    public CustomException(String s, HttpStatus badRequest) {
    }
}
