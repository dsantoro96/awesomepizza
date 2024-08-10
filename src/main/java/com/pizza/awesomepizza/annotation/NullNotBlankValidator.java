package com.pizza.awesomepizza.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullNotBlankValidator implements ConstraintValidator<NullNotBlank, CharSequence> {

    @Override
    public void initialize(NullNotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return value == null || !value.toString().trim().isEmpty();
    }

}
