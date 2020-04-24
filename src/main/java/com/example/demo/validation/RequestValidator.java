package com.example.demo.validation;

import com.example.demo.exception.ValidationException;
import com.example.demo.request.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public class RequestValidator implements ConstraintValidator<RequestValidation, RequestDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);

    @Override
    public boolean isValid(RequestDTO value, ConstraintValidatorContext context) {

        LOGGER.info("REQUEST VALIDATION : " + value.getClass().getName());
        Set<ConstraintViolation<Object>> validates = Validation
                .buildDefaultValidatorFactory()
                .getValidator().validate(value);

         for (ConstraintViolation<Object> validation : validates) {
             throw new ValidationException(""+validation.hashCode(), validation.getMessage());
         }
         return true;
    }
}
