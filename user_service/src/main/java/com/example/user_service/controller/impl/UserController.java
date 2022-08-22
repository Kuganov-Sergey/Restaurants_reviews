package com.example.user_service.controller.impl;

import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.controller.UserControllerI;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController implements UserControllerI {

    @Override
    public UserOutDTO createUser(UserInDTO userInDTO) {
        return null;
    }

    @Override
    public UserOutDTO updateUser(UserInDTO userInDTO, Long id) {
        return null;
    }

    @Override
    public Long deleteUser(Long id) {
        return 0L;
    }

    @Override
    public UserOutDTO getUser(Long id) {
        return null;
    }
}
