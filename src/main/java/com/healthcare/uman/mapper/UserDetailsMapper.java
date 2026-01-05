package com.healthcare.uman.mapper;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {

    public UserDetails toUserDetails(com.healthcare.uman.model.user.User userCredentials) {

        return User.withUsername(userCredentials.getUsername())
                   .password(userCredentials.getPassword())
                   .roles(userCredentials.getRoles().toArray(String[]::new))
                   .build();
    }
}