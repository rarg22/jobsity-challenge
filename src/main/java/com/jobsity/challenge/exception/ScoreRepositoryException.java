package com.jobsity.challenge.exception;

/**
 * ScoreRepositoryException
 */
public class ScoreRepositoryException extends RuntimeException {

    public ScoreRepositoryException() {
        super();
    }

    public ScoreRepositoryException(String message) {
        super(message);
    }

    public ScoreRepositoryException(Throwable cause) {
        super(cause);
    }

}