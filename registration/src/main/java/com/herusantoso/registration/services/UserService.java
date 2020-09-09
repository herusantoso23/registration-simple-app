package com.herusantoso.registration.services;

import com.herusantoso.registration.dtos.RegistrationRequestDto;
import com.herusantoso.registration.dtos.RegistrationResponseDto;

public interface UserService {

    RegistrationResponseDto register(RegistrationRequestDto request);

}
