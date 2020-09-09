package com.herusantoso.registration.service;

import com.herusantoso.registration.dtos.RegistrationRequestDto;
import com.herusantoso.registration.dtos.RegistrationResponseDto;
import com.herusantoso.registration.entities.User;
import com.herusantoso.registration.enums.Gender;
import com.herusantoso.registration.mappers.UserMapper;
import com.herusantoso.registration.repositories.UserRepository;
import com.herusantoso.registration.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    public void register_then_return_RegistrationResponseDto(){
        RegistrationRequestDto request = new RegistrationRequestDto();
        request.setMobileNumber("+6287000000000");
        request.setEmail("herusantoso008@mailinator.com");
        request.setFirstName("Heru");
        request.setLastName("Santoso");
        request.setDateOfBirth(LocalDate.of(1994, 8, 23));
        request.setGender(Gender.MALE);

        User user = new User();
        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());

        User saved = user;
        saved.setId(1L);

        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setId(saved.getId());
        response.setMobileNumber(saved.getMobileNumber());
        response.setEmail(saved.getEmail());
        response.setFirstName(saved.getFirstName());
        response.setLastName(saved.getLastName());
        response.setDateOfBirth(saved.getDateOfBirth());
        response.setGender(saved.getGender());

        Mockito.when(userMapper.toEntity(request, new User()))
                .thenReturn(user);

        Mockito.when(userRepository.save(user))
                .thenReturn(saved);

        Mockito.when(userMapper.toDto(saved, new RegistrationResponseDto()))
                .thenReturn(response);

        RegistrationResponseDto result = userService.register(request);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(response.getId());
        assertThat(result.getMobileNumber()).isEqualTo(response.getMobileNumber());
        assertThat(result.getEmail()).isEqualTo(response.getEmail());
        assertThat(result.getFirstName()).isEqualTo(response.getFirstName());
        assertThat(result.getLastName()).isEqualTo(response.getLastName());
        assertThat(result.getDateOfBirth()).isEqualTo(response.getDateOfBirth());
        assertThat(result.getGender()).isEqualTo(response.getGender());
    }


}
