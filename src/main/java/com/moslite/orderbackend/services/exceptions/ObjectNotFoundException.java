package com.moslite.orderbackend.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7361261773695180125L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
