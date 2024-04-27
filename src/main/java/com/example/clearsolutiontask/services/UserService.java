package com.example.clearsolutiontask.services;

import com.example.clearsolutiontask.exceptions.UserException;
import com.example.clearsolutiontask.models.User;
import com.example.clearsolutiontask.repositories.UserRepository;
import com.example.clearsolutiontask.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Provides services for managing User entities, including saving, finding, deleting, and updating users.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {
    private final UserRepository userRepository;

    /**
     * Saves the given User entity to the database.
     *
     * @param user The User entity to be saved.
     * @return The saved User entity.
     */
    public User save(User user) {
        log.info("Saving user " + user.getEmail());
        return userRepository.save(user);
    }

    /**
     * Retrieves all User entities from the database.
     *
     * @return A list of all User entities.
     */
    public List<User> findAll() {
        log.info("Searching for users in the database");
        return (List<User>) userRepository.findAll();
    }

    /**
     * Deletes the User entity with the specified ID from the database.
     *
     * @param id The ID of the User entity to be deleted.
     */
    public void delete(Long id) {
        log.info(String.format("Removing a user with id %d from the database", id));
        userRepository.deleteById(id);
    }

    /**
     * Updates the User entity with the specified ID using the provided User object.
     *
     * @param userUpdate The User object containing the updated details.
     * @param id         The ID of the User entity to be updated.
     * @return The updated User entity.
     */
    public User update(User userUpdate, Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new UserException(String.format("User with id %d does not exist", id));
        }
        User user = existingUser.get();
        log.info(String.format("The user with id %d is successfully found in the database and the fields are updated",
                id));
        BeanUtils.copyProperties(userUpdate, user, getNullPropertyNames(userUpdate));
        return userRepository.save(user);
    }

    /**
     * Identifies the property names of the given object that have null values.
     *
     * @param source The object to inspect.
     * @return An array of property names with null values.
     */
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * Retrieves users with birthdays within the specified range.
     *
     * @param from The start date of the range.
     * @param to   The end date of the range.
     * @return A list of users with birthdays within the specified range.
     */
    public List<User> findByBirthDateBetween(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new UserException("From date must be less than To date");
        }
        log.info(String.format("The dates are correct, the search for users in the database begins between %s and %s",
                from, to));
        return userRepository.findByBirthDateBetween(from, to);
    }
}
