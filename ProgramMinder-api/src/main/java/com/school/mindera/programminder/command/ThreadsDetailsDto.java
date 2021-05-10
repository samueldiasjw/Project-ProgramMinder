package com.school.mindera.programminder.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadsDetailsDto {
    private long threadId;


    private String title;


    private String post;


    private long userId;


    private int categoryId;
}
