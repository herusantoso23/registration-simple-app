package com.herusantoso.registration.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndonesianMobileNumberValidator implements ConstraintValidator<IndonesianMobileNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.startsWithIgnoreCase(value, "+62");
    }
}
