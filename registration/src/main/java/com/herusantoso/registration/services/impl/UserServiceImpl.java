package com.herusantoso.registration.services.impl;

import com.herusantoso.registration.dtos.RegistrationRequestDto;
import com.herusantoso.registration.dtos.RegistrationResponseDto;
import com.herusantoso.registration.entities.User;
import com.herusantoso.registration.mappers.UserMapper;
import com.herusantoso.registration.repositories.UserRepository;
import com.herusantoso.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Transactional
    @Override
    public RegistrationResponseDto register(RegistrationRequestDto request) {
        User user = userMapper.toEntity(request, new User());
        User saved = userRepository.save(user);
        return userMapper.toDto(saved, new RegistrationResponseDto());
    }

}
