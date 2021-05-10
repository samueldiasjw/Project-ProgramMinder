package com.school.mindera.programminder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ThreadWithThisCategoyIdNotFoundException extends RuntimeException {

    public ThreadWithThisCategoyIdNotFoundException(String message) {
        super(message);
    }
}
