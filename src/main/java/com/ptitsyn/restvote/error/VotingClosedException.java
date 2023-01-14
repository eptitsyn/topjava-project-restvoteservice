package com.ptitsyn.restvote.error;

import org.springframework.http.HttpStatus;

public class VotingClosedException extends AppException {
    public VotingClosedException(String msg) {
        super(HttpStatus.FORBIDDEN, msg);
    }
}
