package com.example.ims.exception;

import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setError("Not Found");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("Bad Request");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

            return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("Bad Request");
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
        HttpServletRequest request) {

    String errorMessage =
            ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error ->
                            error.getField()
                                    + ": "
                                    + error.getDefaultMessage())
                    .reduce(
                            (a, b) -> a + ", " + b)
                    .orElse("Validation error");

    ErrorResponse errorResponse =
            new ErrorResponse();

    errorResponse.setTimestamp(
            LocalDateTime.now());

    errorResponse.setStatus(400);

    errorResponse.setError(
            "Validation Failed");

    errorResponse.setMessage(
            errorMessage);

    errorResponse.setPath(
            request.getRequestURI());

    return ResponseEntity
            .badRequest()
            .body(errorResponse);
}
}
