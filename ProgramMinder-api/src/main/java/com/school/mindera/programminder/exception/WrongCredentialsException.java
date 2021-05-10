package com.school.mindera.programminder.exception;

/**
 * A {@link RentacarApiException} for when the provided credentials are invalid
 */
public class WrongCredentialsException extends ProgramMinderApiException {
    public WrongCredentialsException(String message) {
        super(message);
    }
}
