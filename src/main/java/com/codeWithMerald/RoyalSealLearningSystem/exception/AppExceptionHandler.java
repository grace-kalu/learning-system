package com.codeWithMerald.RoyalSealLearningSystem.exception;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.User;
import com.codeWithMerald.RoyalSealLearningSystem.responses.Response;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintValidation(ConstraintViolationException exception) {
        Response<User> response = new Response<>(HttpStatus.BAD_REQUEST);
        response.addValidationErrors(exception.getConstraintViolations());
        response.setError("Validation Error");
        return buildResponseEntity(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleCustomException(AppException appException) {
        Response<User> response = new Response(appException.getStatus());
        response.setError(appException.getMessage());
        response.setStatus(appException.getStatus());
        return buildResponseEntity(response);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response<User> response = new Response(HttpStatus.BAD_REQUEST);
        response.addValidationError(exception.getBindingResult().getAllErrors());
        response.setError("Validation Error");
        return buildResponseEntity(response);
    }

    @Override
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response<User> response = new Response<>(HttpStatus.BAD_REQUEST);
        response.setError("Invalid input for type: " + exception.getRequiredType());
        response.setDebugMessage("Kindly check the request parameter or path variable");
        return buildResponseEntity(response);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response<User> response = new Response<>(HttpStatus.METHOD_NOT_ALLOWED);
        response.setError(exception.getMethod());
        response.setMessage("Invalid request method");
        response.setDebugMessage("Put in the correct request type");
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(Response<User> response) {
        return new ResponseEntity<>(response, response.getStatus());
    }
}
