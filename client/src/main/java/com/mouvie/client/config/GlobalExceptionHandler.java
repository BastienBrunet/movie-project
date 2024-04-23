package com.mouvie.client.config;

import com.mouvie.client.config.customexception.ElementNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> response(Exception ex, HttpStatus httpStatus) {
        ApiError err = new ApiError(
                LocalDateTime.now(),
                httpStatus,
                ex.getMessage(),
                null);
        return ResponseEntityBuilder.build(err);
    }

    private ResponseEntity<Object> response(Exception ex, HttpStatus httpStatus, Map<String, String> msgParameters) {
        ApiError err = new ApiError(
                LocalDateTime.now(),
                httpStatus,
                ex.getMessage(),
                msgParameters);
        return ResponseEntityBuilder.build(err);
    }

    private ResponseEntity<Object> response(String message, HttpStatus httpStatus, Map<String, String> msgParameters) {
        ApiError err = new ApiError(
                LocalDateTime.now(),
                httpStatus,
                message,
                msgParameters);
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        return response(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Object> handleElementNotFoundException(ElementNotFoundException ex) {
        return response(ex, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return response("Invalid input values", HttpStatus.valueOf(422), errors);
    }
}
