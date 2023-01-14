package com.ptitsyn.restvote.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE, reason = "Not found")
public class NotFoundException extends AppException {

    public NotFoundException(String message) {
        super(HttpStatus.GONE, message);
    }
}
