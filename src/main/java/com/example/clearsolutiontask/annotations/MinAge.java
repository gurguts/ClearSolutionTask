package com.example.clearsolutiontask.annotations;

import com.example.clearsolutiontask.validators.MinAgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * This is a custom constraint annotation to validate that the age of a user is above a certain minimum.
 * It is documented, and its validation logic is implemented in the `MinAgeValidator` class.
 * It can be applied to methods and fields, and it is retained at runtime.
 */
@Documented
@Constraint(validatedBy = MinAgeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {
    String message() default "User must be older than {min.age}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

