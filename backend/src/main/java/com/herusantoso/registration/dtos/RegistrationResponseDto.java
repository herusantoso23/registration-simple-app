package com.herusantoso.registration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herusantoso.registration.enums.Gender;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Data
public class RegistrationResponseDto {

    private Long id;

    @JsonProperty(value = "mobile_number")
    private String mobileNumber;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @JsonProperty(value = "date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty(value = "gender")
    private Gender gender;
}
