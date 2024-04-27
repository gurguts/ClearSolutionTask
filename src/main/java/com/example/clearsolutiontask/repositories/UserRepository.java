package com.example.clearsolutiontask.repositories;

import com.example.clearsolutiontask.models.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for User entities with custom query method.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds users with birthdays between the specified range.
     *
     * @param from The start date of the range.
     * @param to   The end date of the range.
     * @return A list of users with birthdays within the specified range.
     */
    List<User> findByBirthDateBetween(LocalDate from, LocalDate to);
}
