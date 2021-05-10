package com.school.mindera.programminder.command;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * DTO for user update request
 */
@Data
@Builder
public class UpdateUserDto {

    @NotBlank(message = "Must have first name")
    private String firstName;

    @NotBlank(message = "Must have last name")
    private String lastName;

    @NotBlank(message = "Must have license ID")
    private String licenseId;

    @Email(message = "Email must be valid")
    private String email;
}
