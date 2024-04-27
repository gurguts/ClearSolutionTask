package com.example.clearsolutiontask.validators;

import com.example.clearsolutiontask.annotations.MinAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

/**
 * Validates that a user's age is above a certain minimum.
 */
public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {

    @Value("${user.min-age}")
    private int minAge;

    @Override
    public void initialize(MinAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        return birthDate != null && Period.between(birthDate, LocalDate.now()).getYears() >= minAge;
    }
}

