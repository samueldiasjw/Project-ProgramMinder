package com.school.mindera.programminder.command;


import lombok.Builder;
import lombok.Data;

/**
 * Principal information
 * principal definition - entity who can authenticate (user, other service, third-parties...)
 */
@Data
@Builder
public class LoggedInDto {
    private PrincipalDto principalDto;
    private String token;
}
