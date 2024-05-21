package com.piachsecki.financecryptoapp.exception;

public class NotExistingArticleIdException extends RuntimeException {
    public NotExistingArticleIdException(String message) {
        super(message);
    }
}
