package com.besteleben.feature_login.exceptions;

/**
 * this exception should be thrown, when the user can not be found in the system.
 */
public class UserNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}

