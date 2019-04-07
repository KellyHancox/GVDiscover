package com.example.gvdiscoverapp;

public class NoUserFoundException extends Exception {
    public NoUserFoundException() {
        super();
    }
    public NoUserFoundException(String message) {
        super(message);
    }
}
