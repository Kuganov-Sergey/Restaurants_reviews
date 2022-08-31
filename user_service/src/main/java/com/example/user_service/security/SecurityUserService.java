package com.example.user_service.security;

import com.example.user_service.DAO.UserRepository;
import com.example.user_service.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserService implements UserDetailsService {
    private final UserRepository usersRepository;

    public SecurityUserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity byEmail = usersRepository.findByEmail(username);
        return new MyUserDetails(byEmail);
    }
}
