package com.app.kinlock.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public abstract class ErrorHandler extends RuntimeException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex, HttpServletRequest req) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "statusCode", HttpStatus.BAD_REQUEST,
                        "message",    ex.getMessage(),
                        "path",       req.getRequestURI()
                ));
    }

}
