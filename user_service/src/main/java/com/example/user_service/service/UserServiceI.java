package com.example.user_service.service;

import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.exception.UserNotFoundException;

public interface UserServiceI {
    UserOutDTO createUser(UserInDTO userInDTO);
    UserOutDTO updateUser(UserInDTO userInDTO, Long id) throws UserNotFoundException;
    Long deleteUser(Long id) throws UserNotFoundException;
    UserOutDTO getUser(Long id) throws UserNotFoundException;
}
