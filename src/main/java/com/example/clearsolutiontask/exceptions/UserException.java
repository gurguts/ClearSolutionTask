package com.example.clearsolutiontask.exceptions;

/**
 * Custom exception class for user-related errors.
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
