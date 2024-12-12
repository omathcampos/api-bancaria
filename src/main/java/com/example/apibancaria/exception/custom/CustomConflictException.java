package com.example.apibancaria.exception.custom;

import org.springframework.http.HttpStatus;

public class CustomConflictException extends RuntimeException {
    private HttpStatus status;
    private static final String message = "Já existe um cadastro com esses dados.";

    public CustomConflictException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public CustomConflictException(String msg) {
        super(msg);
        this.status = HttpStatus.CONFLICT;
    }

    public CustomConflictException() {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
