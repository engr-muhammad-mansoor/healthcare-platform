package com.healthcare.uman.service;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healthcare.uman.mapper.UserDetailsMapper;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    DatabaseUserDetailsService(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        final User byUsername = userRepository.findByEmail(email);
        if (byUsername == null) {
            throw new AuthenticationServiceException("No user found");
        }
        return userDetailsMapper.toUserDetails(byUsername);
    }
}