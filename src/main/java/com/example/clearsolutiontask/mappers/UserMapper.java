package com.example.clearsolutiontask.mappers;

import com.example.clearsolutiontask.dto.UserDTO;
import com.example.clearsolutiontask.dto.UserUpdateDTO;
import com.example.clearsolutiontask.models.User;
import org.mapstruct.Mapper;

/**
 * Maps User DTOs to User entities.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDTOToUser(UserDTO userDTO);

    User userUpdateDTOToUser(UserUpdateDTO userUpdateDTO);
}
