package com.intuit.craft.exceptions;

public class CraftException extends RuntimeException {


    public CraftException() {
        super();
    }

    public CraftException(String message) {
        super(message);
    }

    public CraftException(String message, Throwable cause) {
        super(message, cause);
    }

    public CraftException(Throwable cause) {
        super(cause);
    }
}
