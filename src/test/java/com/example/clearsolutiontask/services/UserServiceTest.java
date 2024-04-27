package com.example.clearsolutiontask.services;

import com.example.clearsolutiontask.models.User;
import com.example.clearsolutiontask.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setEmail("test@test.com");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals("test@test.com", savedUser.getEmail());
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User();
        user1.setEmail("test1@test.com");

        User user2 = new User();
        user2.setEmail("test2@test.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.findAll();

        assertEquals(2, foundUsers.size());
        assertEquals("test1@test.com", foundUsers.get(0).getEmail());
        assertEquals("test2@test.com", foundUsers.get(1).getEmail());
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;

        doNothing().when(userRepository).deleteById(id);

        userService.delete(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;

        User userUpdate = new User();
        userUpdate.setEmail("updated@test.com");

        User existingUser = new User();
        existingUser.setEmail("test@test.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(userUpdate);

        User updatedUser = userService.update(userUpdate, id);

        assertEquals("updated@test.com", updatedUser.getEmail());
    }

    @Test
    public void testFindByBirthDateBetween() {
        LocalDate from = LocalDate.of(1990, 1, 1);
        LocalDate to = LocalDate.of(2000, 12, 31);

        User user1 = new User();
        user1.setEmail("test1@test.com");
        user1.setBirthDate(LocalDate.of(1995, 5, 5));

        User user2 = new User();
        user2.setEmail("test2@test.com");
        user2.setBirthDate(LocalDate.of(1998, 8, 8));

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findByBirthDateBetween(from, to)).thenReturn(users);

        List<User> foundUsers = userService.findByBirthDateBetween(from, to);

        assertEquals(2, foundUsers.size());
        assertEquals("test1@test.com", foundUsers.get(0).getEmail());
        assertEquals("test2@test.com", foundUsers.get(1).getEmail());
    }

}
