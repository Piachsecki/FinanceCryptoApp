package com.piachsecki.financecryptoapp.exception;

public class NotExistingCustomerException extends RuntimeException {
    public NotExistingCustomerException(String message) {
        super(message);
    }
}
