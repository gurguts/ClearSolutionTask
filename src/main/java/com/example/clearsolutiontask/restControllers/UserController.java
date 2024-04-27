package com.example.clearsolutiontask.restControllers;

import com.example.clearsolutiontask.dto.UserDTO;
import com.example.clearsolutiontask.dto.UserUpdateDTO;
import com.example.clearsolutiontask.mappers.UserMapper;
import com.example.clearsolutiontask.models.User;
import com.example.clearsolutiontask.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController` is a REST controller that handles HTTP requests related to User entities.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Creates a new user with the provided UserDTO and returns the created User.
     *
     * @param userDTO The UserDTO object containing the user details.
     * @return The created User wrapped in a ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("Start creating user: " + userDTO.getEmail());
        User user = userService.save(userMapper.userDTOToUser(userDTO));
        log.info(String.format("User %s was successfully created", user.getEmail()));
        return ResponseEntity.ok(user);
    }

    /**
     * Retrieves all users and returns them in a list.
     *
     * @return A list of all users wrapped in a ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Start output of all users");
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param id The ID of the user to be deleted.
     * @return A confirmation message wrapped in a ResponseEntity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.info(String.format("Start deleting a user with id %d", id));
        userService.delete(id);
        log.info(String.format("User with id %d was successfully deleted", id));
        return ResponseEntity.ok(String.format("User with id %d deleted", id));
    }

    /**
     * Updates a user with the specified ID using the provided UserUpdateDTO and returns the updated User.
     *
     * @param id            The ID of the user to be updated.
     * @param userUpdateDTO The UserUpdateDTO object containing the updated user details.
     * @return The updated User wrapped in a ResponseEntity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        log.info(String.format("Start updating fields for user with id %d", id));
        User updatedUser = userService.update(userMapper.userUpdateDTOToUser(userUpdateDTO), id);
        log.info(String.format("User with id %d was successfully updated", id));
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Retrieves users with birthdays within the specified range.
     *
     * @param from The start date of the range.
     * @param to   The end date of the range.
     * @return A list of users with birthdays within the specified range wrapped in a ResponseEntity.
     */
    @GetMapping("{from}/{to}")
    public ResponseEntity<List<User>> getUsersByBirthDateRange(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        log.info(String.format("Start searching for users with birth dates between %s and %s", from, to));
        return ResponseEntity.ok(userService.findByBirthDateBetween(from, to));
    }
}
