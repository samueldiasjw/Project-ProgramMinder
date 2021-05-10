package com.school.mindera.programminder.controller;

import com.school.mindera.programminder.command.CreateUserDto;
import com.school.mindera.programminder.command.UserDetailsDto;
import com.school.mindera.programminder.command.UserPostingDto;
import com.school.mindera.programminder.enumerators.UserRole;
import com.school.mindera.programminder.persistence.entity.ThreadsEntity;
import com.school.mindera.programminder.persistence.entity.UserEntity;
import com.school.mindera.programminder.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserService userService;

    /**
     * Constructor
     * @param userService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a Spefic User with this username, password, email, avatarName, photo, description
     * @param createUserDto
     * @return
     */

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        LOGGER.info("Request to create - {} and role {}.", createUserDto, UserRole.USER);

        UserDetailsDto userDetailsDto =  userService.createUser(createUserDto, UserRole.USER);

        LOGGER.debug("Service retrieved created User {}", userDetailsDto);
        return new ResponseEntity<>(userDetailsDto, HttpStatus.CREATED);
    }

    /**
     * Request user information who created thread before.
     * @param threadId
     * @return
     */
    @GetMapping("thread/{threadId}")
    public ResponseEntity<List<UserPostingDto>> getUsersInThreadId(@PathVariable long threadId) {
        LOGGER.info("Request Users posting in this ThreadId - {}", threadId);

        List<UserPostingDto> userPostingDtoList = userService.getUsersInThreadId(threadId);

        return new ResponseEntity<>(userPostingDtoList, HttpStatus.OK);
    }

    /**
     * Get post user information
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable long userId) {
        LOGGER.info("Request User with Id - {}", userId);

        UserDetailsDto userDetailsDto = userService.getUserById(userId);

        return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);
    }

    /**
     * Get users information to put in threads Title - Photo - "BY: USER"
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<UserPostingDto>> getUsersInCategpryId(@PathVariable long categoryId) {
        LOGGER.info("Request users posting in specific category - {}", categoryId);

        List<UserPostingDto> userPostingDtoList = userService.getUsersInCategoryId(categoryId);

        return new ResponseEntity<>(userPostingDtoList, HttpStatus.OK);
    }
}
