package com.example.user_service.controller.impl;

import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.controller.UserControllerI;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerI {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserOutDTO createUser(UserInDTO userInDTO) throws UserEmailIsAlreadyExist {
        return userService.createUser(userInDTO);
    }

    @Override
    public UserOutDTO updateUser(UserEntity userEntity, Long id) throws UserNotFoundException {
        return userService.updateUser(userEntity, id);
    }

    @Override
    public Long deleteUser(Long id) throws UserNotFoundException {
        return userService.deleteUser(id);
    }

    @Override
    public UserOutDTO getUser(Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @Override
    public Page<UserEntity> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }
}
