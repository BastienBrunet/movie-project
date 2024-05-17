package com.mouvie.auth.config;

import com.mouvie.auth.config.customexception.ElementNotFoundException;
import com.mouvie.auth.config.customexception.ForbiddenException;
import com.mouvie.auth.config.customexception.InvalidFileFormatException;
import com.mouvie.auth.config.customexception.LoginAlreadyExistsException;
import com.mouvie.library.exception.StorageFileNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        return response(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return response(ex, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<Object> handleStorageFileNotFoundException(StorageFileNotFoundException ex) {
        return response(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseEntity<Object> handleInvalidFileFormatException(InvalidFileFormatException ex) {
        return response(ex, HttpStatus.valueOf(422));
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
    
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<?> handleLoginAlreadyExistsException(LoginAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY); // 422 status code
    }
}
