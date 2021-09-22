package com.moslite.orderbackend.services.exceptions;

public class FileException extends RuntimeException {

    private static final long serialVersionUID = 7361261773695180125L;

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
