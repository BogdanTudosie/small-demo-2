package com.bogdan.small_demo_2.mdm_service.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;
import java.time.Instant;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleInvalidArgs(IllegalArgumentException ex) {
        return new ResponseEntity<>(
            Map.of("error", ex.getMessage(),
            "timestamp", Instant.now()
        ),
        HttpStatus.BAD_REQUEST);
    }
}
