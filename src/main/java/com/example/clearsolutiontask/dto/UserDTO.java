package com.example.clearsolutiontask.dto;

import com.example.clearsolutiontask.annotations.MinAge;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents a Data Transfer Object for User with various fields and validation constraints.
 */
@Data
public class UserDTO {
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Past(message = "Birthday must be earlier than current date")
    @MinAge(message = "{error.user.age}")
    private LocalDate birthDate;

    private String address;

    private String phoneNumber;
}
