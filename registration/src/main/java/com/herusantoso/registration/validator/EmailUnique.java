package com.herusantoso.registration.validator;

import java.lang.annotation.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailUniqueValidator.class)
@Documented
public @interface EmailUnique {

    String message() default "Please enter another email, email already used";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
