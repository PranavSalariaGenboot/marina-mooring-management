package com.marinamooringmanagement.security.exception;

import javax.naming.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException {
    public EmailNotFoundException(String message, Throwable cause) {
        super(message);
    }

    public EmailNotFoundException(String message) {
        super(message);
    }
}
