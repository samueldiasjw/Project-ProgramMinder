package com.school.mindera.programminder.controller;

import com.school.mindera.programminder.command.CredentialsDto;
import com.school.mindera.programminder.command.LoggedInDto;
import com.school.mindera.programminder.command.PrincipalDto;
import com.school.mindera.programminder.error.ErrorMessages;
import com.school.mindera.programminder.exception.ProgramMinderApiException;
import com.school.mindera.programminder.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.school.mindera.programminder.security.CookieAuthFilter.COOKIE_NAME;

/**
 * REST controller responsible for authentication operations
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login user with email and password
     * @param credentials
     * @return {@link LoggedInDto} with user info and jwt token
     */
    @PostMapping("/login")
    public ResponseEntity<LoggedInDto> login(@RequestBody CredentialsDto credentials) {
        LOGGER.info("Request to login user with email {}", credentials.getEmail());

        LoggedInDto loggedIn;
        try {
            loggedIn = authService.loginUser(credentials);

            ResponseCookie cookie = ResponseCookie
                    .from(COOKIE_NAME, loggedIn.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .maxAge(24 * 60 * 60)
                    .path("/")
                    .build();

            LOGGER.info("User logged in successfully. Retrieving jwt token and setting cookie");

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(loggedIn);

        } catch (ProgramMinderApiException e) {
            // Since RentacarApiException exceptions are thrown by us, we just throw them
            throw e;

        } catch (Exception e) {
            // With all others exceptions we log them and throw a generic exception
            LOGGER.error("Failed to loging user - {}", credentials, e);
            throw new ProgramMinderApiException(ErrorMessages.OPERATION_FAILED, e);
        }
    }


    /**
     * Logout user and delete cookie
     * @param principal
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal PrincipalDto principal) {
        LOGGER.info("Request to logout user with id {}", principal.getUserId());

        try {
            SecurityContextHolder.clearContext();

            ResponseCookie deleteCookie = ResponseCookie
                    .from(COOKIE_NAME, null)
                    .httpOnly(true)
                    .secure(false)
                    .maxAge(0L)
                    .path("/")
                    .build();

            LOGGER.info("Successfully logged out. Deleting cookie.");

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                    .build();

        } catch (Exception e) {
            // With all others exceptions we log them and throw a generic exception
            LOGGER.error("Failed to logout user - {}", principal.getUserId(), e);
            throw new ProgramMinderApiException(ErrorMessages.OPERATION_FAILED, e);
        }
    }
}
