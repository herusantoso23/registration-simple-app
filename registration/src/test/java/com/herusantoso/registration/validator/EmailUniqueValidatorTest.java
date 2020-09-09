package com.herusantoso.registration.validator;

import com.herusantoso.registration.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EmailUniqueValidatorTest {

    @InjectMocks
    private EmailUniqueValidator emailUniqueValidator;

    @Mock
    private UserRepository userRepository;

    @Test
    public void email_exist_then_return_false(){
        String value = "herusantoso@mailinator.com";

        Mockito.when(userRepository.existsByEmail(value))
                .thenReturn(true);

        boolean res = emailUniqueValidator.isValid(value, null);
        assertThat(res).isFalse();
    }

    @Test
    public void email_exist_then_return_true(){
        String value = "herusantoso@mailinator.com";

        Mockito.when(userRepository.existsByEmail(value))
                .thenReturn(false);

        boolean res = emailUniqueValidator.isValid(value, null);
        assertThat(res).isTrue();
    }

    @Test
    public void email_empty_then_return_true(){
        String value = "";

        boolean res = emailUniqueValidator.isValid(value, null);
        assertThat(res).isTrue();
    }

}
