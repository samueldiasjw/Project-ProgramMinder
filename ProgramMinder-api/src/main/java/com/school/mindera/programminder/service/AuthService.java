package com.school.mindera.programminder.service;

import com.school.mindera.programminder.command.CredentialsDto;
import com.school.mindera.programminder.command.LoggedInDto;
import com.school.mindera.programminder.command.PrincipalDto;

/**
 * Common interface for authorization operations
 */
public interface AuthService {

    /**
     * Login user
     * @param credentialsDto
     * @return {@link LoggedInDto} logged in user details
     */
    LoggedInDto loginUser(CredentialsDto credentialsDto);

    /**
     * Validate token
     * @param token
     * @return {@link PrincipalDto} principal authenticated
     */
    PrincipalDto validateToken(String token);
}
