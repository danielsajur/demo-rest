package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RequestValidator.class)
public @interface RequestValidation {
    String message() default
            "End date must be after begin date and both must be in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}