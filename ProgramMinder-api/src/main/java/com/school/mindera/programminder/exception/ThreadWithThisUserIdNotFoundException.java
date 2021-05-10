package com.school.mindera.programminder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ThreadWithThisUserIdNotFoundException extends RuntimeException {

    public ThreadWithThisUserIdNotFoundException(String message) {
        super(message);
    }
}
