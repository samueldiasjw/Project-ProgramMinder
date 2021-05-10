package com.school.mindera.programminder.service;

import com.school.mindera.programminder.command.CreateUserDto;
import com.school.mindera.programminder.command.UserDetailsDto;
import com.school.mindera.programminder.command.UserPostingDto;
import com.school.mindera.programminder.converter.UserConverter;
import com.school.mindera.programminder.enumerators.UserRole;
import com.school.mindera.programminder.error.ErrorMessages;
import com.school.mindera.programminder.exception.*;
import com.school.mindera.programminder.persistence.entity.ThreadsEntity;
import com.school.mindera.programminder.persistence.entity.UserEntity;
import com.school.mindera.programminder.persistence.repository.ThreadRepository;
import com.school.mindera.programminder.persistence.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * Constructor
     * @param userRepository
     * @param threadRepository
     * @param passwordEncoder
     */
    public UserService(UserRepository userRepository, ThreadRepository threadRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.threadRepository = threadRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Convert User to userEntity. Create user and set a Role
     * @param userRegistrationDto
     * @param userRole
     * @return
     */
    public UserDetailsDto createUser(CreateUserDto userRegistrationDto, UserRole userRole) throws UserAlreadyExistsException {

        // Build UserEntity
        UserEntity userEntity = UserConverter.fromCreateUserDtoToUserEntity(userRegistrationDto);
        userEntity.setRole(userRole);

        // Encrypt password
        String encryptedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        // Set encrypted password
        userEntity.setEncryptedPassword(encryptedPassword);

        // Persist user into database
        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException sqlException) {
            LOGGER.error("Duplicated email - {}", userEntity.getEmail(), sqlException);
            throw new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
        } catch (Exception e) {
            LOGGER.error("Failed while saving user into database {}", userEntity, e);
            throw new DataBaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        // Build UserDetailsDto to return to the client
        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }

    /**
     * Get users in thread with specific thread id. Helping FE with user data
     * @param threadId
     * @return
     */
    public List<UserPostingDto> getUsersInThreadId(long threadId) {
        Iterable<UserEntity> postsListSpecificThreadByCategoryId = userRepository.findPostsByThreadId(threadId);

        ThreadsEntity threadsEntity = threadRepository.findById(threadId)
                .orElseThrow(() -> {
                    LOGGER.error("Try to get a Thread don't exist, THREAD ID - {}", threadId);
                    return new ThreadIdFoundException(ErrorMessages.THREAD_ID_NOT_FOUND);
                });

        UserEntity ownerOfThread = userRepository.findById(threadsEntity.getUserId())
                .orElseThrow(() -> {
                    LOGGER.error("Try to get a Thread don't exist, THREAD ID - {}", threadId);
                    return new ThreadIdFoundException(ErrorMessages.THREAD_ID_NOT_FOUND);
                });


        List<UserPostingDto> userPostingDtoList = new ArrayList<>();
        userPostingDtoList.add(UserConverter.fromUserEntityToUserPostingDto(ownerOfThread));

        if(postsListSpecificThreadByCategoryId.toString().equals("[]") && userPostingDtoList.isEmpty()){
            LOGGER.info("This CategoryId don't exist or Threads are not createad in this Category!");
            throw new ThreadWithThisCategoyIdNotFoundException(ErrorMessages.DONT_EXIST_THREADS);
        }

        for (UserEntity user : postsListSpecificThreadByCategoryId) {
            userPostingDtoList.add(UserConverter.fromUserEntityToUserPostingDto(user));
        }

        LOGGER.info("Threads Finded, returning!");
        return  userPostingDtoList;
    }


    /**
     * Get user Details for posts information about guy who did what
     * @param userId
     * @return
     */
    public UserDetailsDto getUserById(long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                                .orElseThrow(() -> {
            LOGGER.error("Try to get a User don't exist, USER ID - {}", userId);
            return new UserNotFoundException(ErrorMessages.USER_NOT_FOUND);
        });


        // Build UserDetailsDto to return to the client
        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }


    /**
     * Get users who create threads.
     * @param categoryId
     * @return
     */
    public List<UserPostingDto> getUsersInCategoryId(long categoryId) {
        Iterable<UserEntity> postsListSpecificThreadByCategoryId = userRepository.findPostsByCategoryId(categoryId);


        List<UserPostingDto> userPostingDtoList = new ArrayList<>();

        if(postsListSpecificThreadByCategoryId.toString().equals("[]")){
            LOGGER.info("This CategoryId don't exist or Threads are not createad in this Category!");
            throw new ThreadWithThisCategoyIdNotFoundException(ErrorMessages.DONT_EXIST_THREADS);
        }

        for (UserEntity user : postsListSpecificThreadByCategoryId) {
            userPostingDtoList.add(UserConverter.fromUserEntityToUserPostingDto(user));
        }

        LOGGER.info("Threads Finded, returning!");
        return  userPostingDtoList;
    }
}
