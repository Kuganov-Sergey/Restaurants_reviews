package com.example.user_service.security;

import com.example.user_service.entity.RoleEntity;
import com.example.user_service.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final UserEntity entity;

    public MyUserDetails(UserEntity entity) {
        this.entity = entity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        for (RoleEntity role : entity.getRoles()) {
            list.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return entity.getPassword();
    }

    @Override
    public String getUsername() {
        return entity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO: add expiration date in DB
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO: add lock flag in DB
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO: add password expirition date
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO: add lock flag in DB
        return true;
    }
}
