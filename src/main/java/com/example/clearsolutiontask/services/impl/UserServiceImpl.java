package com.example.clearsolutiontask.services.impl;

import com.example.clearsolutiontask.models.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Defines the service operations for managing User entities.
 */
public interface UserServiceImpl {
    User save(User user);

    List<User> findAll();

    void delete(Long id);

    User update(User user, Long id);

    List<User> findByBirthDateBetween(LocalDate from, LocalDate to);
}
