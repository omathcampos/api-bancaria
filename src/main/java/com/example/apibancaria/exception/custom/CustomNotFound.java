package com.example.apibancaria.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CustomNotFound extends RuntimeException {
    private HttpStatus status;
    private static final String message = "Pessoa buscada não encontrada";

    public CustomNotFound(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public CustomNotFound(String msg) {
        super(msg);
        this.status = HttpStatus.NOT_FOUND;
    }

    public CustomNotFound(String msg, Throwable cause) {
        super(msg, cause);
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public CustomNotFound() {
    }
}
