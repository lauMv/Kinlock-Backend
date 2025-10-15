package com.app.kinlock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex, ServerHttpRequest request) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "statusCode", HttpStatus.BAD_REQUEST.value(),
                        "message", ex.getMessage(),
                        "path", request.getURI().getPath(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}