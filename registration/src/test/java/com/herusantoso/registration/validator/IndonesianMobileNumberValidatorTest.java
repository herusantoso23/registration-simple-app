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
public class IndonesianMobileNumberValidatorTest {

    @InjectMocks
    private IndonesianMobileNumberValidator indonesianMobileNumberValidator;

    @Mock
    private UserRepository userRepository;

    @Test
    public void indoensian_mobile_number_then_return_true(){
        String value = "+6280000000000";

        boolean res = indonesianMobileNumberValidator.isValid(value, null);
        assertThat(res).isTrue();
    }

    @Test
    public void indoensian_mobile_number_then_return_false(){
        String value = "+7280000000000";

        boolean res = indonesianMobileNumberValidator.isValid(value, null);
        assertThat(res).isFalse();
    }

}
