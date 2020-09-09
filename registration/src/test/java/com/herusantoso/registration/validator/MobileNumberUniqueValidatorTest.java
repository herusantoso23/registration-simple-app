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
public class MobileNumberUniqueValidatorTest {

    @InjectMocks
    private MobileNumberUniqueValidator mobileNumberUniqueValidator;

    @Mock
    private UserRepository userRepository;

    @Test
    public void mobile_number_exist_then_return_false(){
        String value = "+6280000000000";

        Mockito.when(userRepository.existsByMobileNumber(value))
                .thenReturn(true);

        boolean res = mobileNumberUniqueValidator.isValid(value, null);
        assertThat(res).isFalse();
    }

    @Test
    public void mobile_number_exist_then_return_true(){
        String value = "+6280000000000";

        Mockito.when(userRepository.existsByMobileNumber(value))
                .thenReturn(false);

        boolean res = mobileNumberUniqueValidator.isValid(value, null);
        assertThat(res).isTrue();
    }

    @Test
    public void mobile_number_empty_then_return_true(){
        String value = "";

        boolean res = mobileNumberUniqueValidator.isValid(value, null);
        assertThat(res).isTrue();
    }

}
