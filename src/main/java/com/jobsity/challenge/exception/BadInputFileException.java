package com.jobsity.challenge.exception;

/**
 * BadInputFileException
 */
public class BadInputFileException extends RuntimeException {

    public BadInputFileException(String message) {
        super(message);
    }

    public BadInputFileException(Throwable cause) {
        super(cause);
    }
}