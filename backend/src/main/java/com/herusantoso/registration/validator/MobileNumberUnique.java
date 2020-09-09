package com.herusantoso.registration.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileNumberUniqueValidator.class)
@Documented
public @interface MobileNumberUnique {

    String message() default "Please enter another mobile number, mobile number already used";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
