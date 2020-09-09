package com.herusantoso.registration.controllers;

import com.herusantoso.registration.dtos.RegistrationRequestDto;
import com.herusantoso.registration.dtos.RegistrationResponseDto;
import com.herusantoso.registration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/user")
    public ResponseEntity<RegistrationResponseDto> register(@Valid @RequestBody RegistrationRequestDto request){
        RegistrationResponseDto res = userService.register(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
