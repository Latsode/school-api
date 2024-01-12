package com.softgen.school.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError("Internal Server Error");
        response.setMessage(ex.getMessage());
        response.setPath(request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        ApiErrorResponse response = createErrorResponse(ex, request, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        ApiErrorResponse response = createErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
        response.setMessage("Validation failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private ApiErrorResponse createErrorResponse(Exception ex, WebRequest request, HttpStatus status) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setPath(request.getDescription(false));
        return response;
    }
}