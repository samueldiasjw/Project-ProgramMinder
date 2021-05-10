package com.school.mindera.programminder.command;

import com.school.mindera.programminder.enumerators.UserRole;
import lombok.Builder;
import lombok.Data;

/**
 * Principal information
 * principal definition - entity who can authenticate (user, other service, third-parties...)
 */
@Data
@Builder
public class PrincipalDto {
    private Long userId;
    private String userName;
    private String avatarName;
    private UserRole userRole;
}
