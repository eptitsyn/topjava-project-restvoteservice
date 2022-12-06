package com.ptitsyn.restvote.error;

public class DataConflictException extends RuntimeException {

    public DataConflictException(String msg) {
        super(msg);
    }
}