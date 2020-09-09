package com.herusantoso.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herusantoso.registration.RegistrationApplication;
import com.herusantoso.registration.dtos.RegistrationRequestDto;
import com.herusantoso.registration.dtos.RegistrationResponseDto;
import com.herusantoso.registration.enums.Gender;
import com.herusantoso.registration.repositories.UserRepository;
import com.herusantoso.registration.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RegistrationApplication.class)
@WebAppConfiguration
public class UserControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void register_success_then_return_registration_response_dto() throws Exception {
        RegistrationRequestDto request = new RegistrationRequestDto();
        request.setMobileNumber("+6287000000000");
        request.setEmail("herusantoso008@mailinator.com");
        request.setFirstName("Heru");
        request.setLastName("Santoso");
        request.setGender(Gender.MALE);

        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setId(1L);
        response.setMobileNumber(request.getMobileNumber());
        response.setEmail(request.getEmail());
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());
        response.setGender(request.getGender());

        Mockito.when(userService.register(request)).thenReturn(response);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
			    .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void register_failed_then_throw_validation_exception() throws Exception {
        RegistrationRequestDto request = new RegistrationRequestDto();
        request.setFirstName("Heru");
        request.setLastName("Santoso");
        request.setGender(Gender.MALE);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void register_failed_then_throw_not_readable_exception() throws Exception {
        RegistrationRequestDto request = new RegistrationRequestDto();
        request.setFirstName("Heru");
        request.setLastName("Santoso");
        request.setGender(Gender.MALE);
        request.setMobileNumber("+6287000000000");
        request.setEmail("herusantoso008@mailinator.com");
        request.setFirstName("Heru");
        request.setLastName("Santoso");
        request.setGender(Gender.MALE);
        request.setDateOfBirth(LocalDate.of(1994, 8, 23));

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}
