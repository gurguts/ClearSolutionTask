package com.example.clearsolutiontask.dto;

import com.example.clearsolutiontask.annotations.MinAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents a data transfer object for updating a user with various fields and validation constraints.
 */
@Data
public class UserUpdateDTO {
    @Email
    private String email;

    private String firstName;

    private String lastName;

    @Past(message = "Birthday must be earlier than current date")
    @MinAge(message = "{error.user.age}")
    private LocalDate birthDate;

    private String address;

    private String phoneNumber;
}

