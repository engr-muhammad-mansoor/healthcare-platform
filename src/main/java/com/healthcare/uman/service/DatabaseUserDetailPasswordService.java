package com.healthcare.uman.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

import com.healthcare.uman.mapper.UserDetailsMapper;
import com.healthcare.uman.model.user.User;
import com.healthcare.uman.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DatabaseUserDetailPasswordService implements UserDetailsPasswordService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    DatabaseUserDetailPasswordService(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User userCredentials = userRepository.findByUsername(user.getUsername());
        userCredentials.setPassword(newPassword);
        return userDetailsMapper.toUserDetails(userCredentials);
    }
}