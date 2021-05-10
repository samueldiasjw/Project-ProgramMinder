package com.school.mindera.programminder.exception;

public class ProgramMinderApiException extends RuntimeException{
    public ProgramMinderApiException() {
    }

    public ProgramMinderApiException(String message) {
        super(message);
    }

    public ProgramMinderApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgramMinderApiException(Throwable cause) {
        super(cause);
    }

    public ProgramMinderApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
