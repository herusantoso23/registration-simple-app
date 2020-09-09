package com.herusantoso.registration.mappers;

import com.herusantoso.registration.dtos.RegistrationRequestDto;
import com.herusantoso.registration.dtos.RegistrationResponseDto;
import com.herusantoso.registration.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegistrationResponseDto toDto(User user, @MappingTarget RegistrationResponseDto dto);

    User toEntity(RegistrationRequestDto dto, @MappingTarget User user);

}
