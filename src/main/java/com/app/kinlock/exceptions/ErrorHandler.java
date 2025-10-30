package com.app.kinlock.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({EntityNotFoundException.class,
            DuplicatedException.class,
            HasAssociatedEntityException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(
            Exception ex,
            HttpServletRequest request) {          // correct type

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "statusCode", HttpStatus.BAD_REQUEST.value(),
                        "message", ex.getMessage(),
                        "path", request.getRequestURI(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}