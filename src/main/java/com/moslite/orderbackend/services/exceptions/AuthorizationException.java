package com.moslite.orderbackend.services.exceptions;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 7361261773695180125L;

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
