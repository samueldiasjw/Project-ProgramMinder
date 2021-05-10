package com.school.mindera.programminder.error;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Error {

    Date timestamp;
    String message;
    String method;
    String exception;
    String path;
}
