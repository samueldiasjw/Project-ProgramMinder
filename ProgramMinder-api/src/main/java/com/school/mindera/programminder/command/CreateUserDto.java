package com.school.mindera.programminder.command;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateUserDto {

    @NotBlank(message = "Must have first name")
    private String username;

    @NotBlank(message = "Must have password")
    private String password;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Must have Avatar Name")
    private String avatarName;

    private String photo;

    private String description;

    /**
     * Override to String to avoid show the password
     * in the logs if printing the entire object
     * @return
     */
    @Override
    public String toString() {
        return "CreateUserDto{" +
                "firstName='" + username + '\'' +
                ", AvatarName='" + avatarName + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", password='***'" +
                '}';
    }
}
