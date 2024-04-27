package com.example.clearsolutiontask.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents a User entity in the database.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
}
