package com.example.gvdiscoverapp;

/**
 * NoUserFoundException determines if a user is found.
 */
class NoUserFoundException extends Exception {
    /**
     * Super for the no user found exception.
     */
    public NoUserFoundException() {
        super();
    }

    /**
     * Returns the message for the the no user found exception.
     * @param message super message
     */
    public NoUserFoundException(final String message) {
        super(message);
    }
}