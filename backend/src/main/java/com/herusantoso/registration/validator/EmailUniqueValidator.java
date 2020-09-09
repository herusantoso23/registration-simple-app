package com.herusantoso.registration.validator;

import com.herusantoso.registration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value)) return true;

        boolean emailExist = userRepository.existsByEmail(value);
        return !emailExist;
    }
}
