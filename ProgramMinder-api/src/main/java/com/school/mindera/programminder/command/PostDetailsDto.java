package com.school.mindera.programminder.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDetailsDto {
    private long postId;


    private String post;


    private long userId;


    private long threadId;
}
