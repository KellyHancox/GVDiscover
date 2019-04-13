package com.example.gvdiscoverapp;

/**
 * NoUserFoundException determines if a user is found.
 */
class NoUserFoundException extends Exception {
    /**
     * Super for the no user found exception.
     */
    NoUserFoundException() {
        super();
    }

    /**
     * Returns the message for the the no user found exception.
     * @param message super message
     */
    NoUserFoundException(final String message) {
        super(message);
    }
}
