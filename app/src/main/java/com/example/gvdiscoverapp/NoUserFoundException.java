package com.example.gvdiscoverapp;

/**
 * NoUserFoundException should be thrown when user is not found.
 */
public class NoUserFoundException extends Exception {
    /**
     * Constructor for the exception without the message.
     * */
    public NoUserFoundException() {
        super();
    }

    /**
     * Constructor for the exception with the message.
     * */
    public NoUserFoundException(String message) {
        super(message);
    }
}
