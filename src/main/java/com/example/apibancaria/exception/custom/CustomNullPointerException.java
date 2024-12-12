package com.example.apibancaria.exception.custom;

import org.springframework.http.HttpStatus;

public class CustomNullPointerException extends RuntimeException {
    private HttpStatus status;
    private static final String message = "Você deve preencher todos os campos";

    public CustomNullPointerException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public CustomNullPointerException(String msg) {
        super(msg);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomNullPointerException(String msg, Throwable cause) {
        super(msg, cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public CustomNullPointerException() {
    }
}
