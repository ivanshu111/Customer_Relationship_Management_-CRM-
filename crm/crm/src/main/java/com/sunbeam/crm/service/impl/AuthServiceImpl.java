package com.sunbeam.crm.service.impl;

import com.sunbeam.crm.dto.RegisterRequest;
import com.sunbeam.crm.entity.Role;
import com.sunbeam.crm.entity.Users;
import com.sunbeam.crm.repository.UserRepository;
import com.sunbeam.crm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void register(RegisterRequest registerRequest) {

        //check for duplicate email
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        //create user
        Users user= new Users();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.valueOf(registerRequest.getRole()));

        userRepository.save(user);
    }
}
