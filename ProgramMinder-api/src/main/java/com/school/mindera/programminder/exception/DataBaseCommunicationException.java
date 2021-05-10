package com.school.mindera.programminder.exception;

public class DataBaseCommunicationException extends RuntimeException {

    public DataBaseCommunicationException(String message, Exception e) {
        super(message);
    }
}
