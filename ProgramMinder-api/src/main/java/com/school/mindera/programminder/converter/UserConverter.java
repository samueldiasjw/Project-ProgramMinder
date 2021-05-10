package com.school.mindera.programminder.converter;

import com.school.mindera.programminder.command.CreateUserDto;
import com.school.mindera.programminder.command.PrincipalDto;
import com.school.mindera.programminder.command.UserDetailsDto;
import com.school.mindera.programminder.command.UserPostingDto;
import com.school.mindera.programminder.persistence.entity.UserEntity;

public class UserConverter {

    public static UserEntity fromCreateUserDtoToUserEntity(CreateUserDto createUserDto) {
        return UserEntity.builder()
                .username(createUserDto.getUsername())
                .encryptedPassword(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .avatarName(createUserDto.getAvatarName())
                .photo(createUserDto.getPhoto())
                .description(createUserDto.getDescription())
                .build();
    }

    /**
     * Transform UserEntity in UserDetailsDto
     * @param userEntity
     * @return
     */
    public static UserDetailsDto fromUserEntityToUserDetailsDto(UserEntity userEntity) {
        return UserDetailsDto.builder()
                .id(userEntity.getUserId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .avatarName(userEntity.getAvatarName())
                .photo(userEntity.getPhoto())
                .description(userEntity.getDescription())
                .build();
    }


    public static UserPostingDto fromUserEntityToUserPostingDto(UserEntity userEntity) {
        return UserPostingDto.builder()
                .userId(userEntity.getUserId())
                .avatarName(userEntity.getAvatarName())
                .photo(userEntity.getPhoto())
                .role(userEntity.getRole())
                .build();
    }

    public static PrincipalDto fromUserEntityToPrincipalDto(UserEntity userEntity) {
        return PrincipalDto.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUsername())
                .avatarName(userEntity.getAvatarName())
                .userRole(userEntity.getRole())
                .build();
    }
}
