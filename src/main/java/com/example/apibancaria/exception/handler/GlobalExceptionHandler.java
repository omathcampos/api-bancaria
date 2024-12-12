package com.example.apibancaria.exception.handler;


import com.example.apibancaria.exception.custom.CustomConflictException;
import com.example.apibancaria.exception.custom.CustomNotFound;
import com.example.apibancaria.exception.custom.CustomNullPointerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomNotFound.class)
    private ResponseEntity<String> notFoundHandler(CustomNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CustomConflictException.class)
    private ResponseEntity<String> customConflictHandler(CustomConflictException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(CustomNullPointerException.class)
    private ResponseEntity<String> customNullPointerException(CustomNullPointerException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
