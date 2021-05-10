package com.school.mindera.programminder.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {


    private long id;


    private String username;


    private String email;


    private String avatarName;


    private String photo;

    private String description;
}
