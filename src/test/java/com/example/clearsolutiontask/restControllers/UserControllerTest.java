package com.example.clearsolutiontask.restControllers;

import com.example.clearsolutiontask.dto.UserDTO;
import com.example.clearsolutiontask.dto.UserUpdateDTO;
import com.example.clearsolutiontask.mappers.UserMapper;
import com.example.clearsolutiontask.models.User;
import com.example.clearsolutiontask.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    UserMapper userMapper;

    @Test
    public void testCreateUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");

        User user = new User();
        user.setEmail("test@test.com");

        when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.createUser(userDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("test@test.com", Objects.requireNonNull(responseEntity.getBody()).getEmail());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setEmail("test1@test.com");

        User user2 = new User();
        user2.setEmail("test2@test.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, Objects.requireNonNull(responseEntity.getBody()).size());
        assertEquals("test1@test.com", responseEntity.getBody().get(0).getEmail());
        assertEquals("test2@test.com", responseEntity.getBody().get(1).getEmail());
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;

        doNothing().when(userService).delete(id);

        ResponseEntity<String> responseEntity = userController.deleteUser(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User with id 1 deleted", responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("updated@test.com");

        User updatedUser = new User();
        updatedUser.setEmail("updated@test.com");

        when(userMapper.userUpdateDTOToUser(userUpdateDTO)).thenReturn(updatedUser);
        when(userService.update(updatedUser, id)).thenReturn(updatedUser);

        ResponseEntity<User> responseEntity = userController.updateUser(id, userUpdateDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("updated@test.com", Objects.requireNonNull(responseEntity.getBody()).getEmail());
    }

    @Test
    public void testGetUsersByBirthDateRange() {
        LocalDate from = LocalDate.of(1990, 1, 1);
        LocalDate to = LocalDate.of(2000, 12, 31);

        User user1 = new User();
        user1.setEmail("test1@test.com");
        user1.setBirthDate(LocalDate.of(1995, 5, 5));

        User user2 = new User();
        user2.setEmail("test2@test.com");
        user2.setBirthDate(LocalDate.of(1998, 8, 8));

        List<User> users = Arrays.asList(user1, user2);

        when(userService.findByBirthDateBetween(from, to)).thenReturn(users);

        ResponseEntity<List<User>> responseEntity = userController.getUsersByBirthDateRange(from, to);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, Objects.requireNonNull(responseEntity.getBody()).size());
        assertEquals("test1@test.com", responseEntity.getBody().get(0).getEmail());
        assertEquals("test2@test.com", responseEntity.getBody().get(1).getEmail());
    }
}