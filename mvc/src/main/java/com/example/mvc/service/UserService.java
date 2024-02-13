package com.example.mvc.service;


import com.example.mvc.dto.RegistrationDto;
import com.example.mvc.models.UserEntity;


public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
