package com.herusantoso.registration.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herusantoso.registration.enums.Gender;
import com.herusantoso.registration.validator.EmailUnique;
import com.herusantoso.registration.validator.IndonesianMobileNumber;
import com.herusantoso.registration.validator.MobileNumberUnique;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegistrationRequestDto {

    @IndonesianMobileNumber
    @MobileNumberUnique
    @NotBlank(message = "Please enter mobile number. Mobile number is required")
    @Size(max = 14, message = "Max character of mobile number is {max}")
    @JsonProperty(value = "mobile_number")
    private String mobileNumber;

    @EmailUnique
    @NotBlank(message = "Please enter email. Email is required")
    @Email(message = "Please enter valid email")
    @Size(max = 255, message = "Max character of email is {max}")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "Please enter first name. First name is required")
    @Size(max = 100, message = "Max character of first name is {max}")
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotBlank(message = "Please enter last name. Last name is required")
    @Size(max = 100, message = "Max character of last name is {max}")
    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty(value = "date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty(value = "gender")
    private Gender gender;

}
