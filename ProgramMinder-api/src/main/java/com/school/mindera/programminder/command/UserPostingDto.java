package com.school.mindera.programminder.command;

import com.school.mindera.programminder.enumerators.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPostingDto {

    private long userId;


    private String avatarName;


    private String photo;


    private UserRole role;
}
