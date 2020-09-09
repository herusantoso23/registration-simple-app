package com.herusantoso.registration.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IndonesianMobileNumberValidator.class)
@Documented
public @interface IndonesianMobileNumber {

    String message() default "Please enter valid indonesian phone number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
