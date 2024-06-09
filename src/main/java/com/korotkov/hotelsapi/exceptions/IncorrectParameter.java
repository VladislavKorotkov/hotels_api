package com.korotkov.hotelsapi.exceptions;

public class IncorrectParameter extends RuntimeException{
    public IncorrectParameter(String message) {
        super(message);
    }

    public IncorrectParameter(String message, Throwable cause) {
        super(message, cause);
    }
}
