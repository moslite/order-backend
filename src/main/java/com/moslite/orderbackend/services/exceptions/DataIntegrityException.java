package com.moslite.orderbackend.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 7361261773695180125L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
