package com.example.clearsolutiontask.validators;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinAgeValidatorTest {

    @Test
    public void testIsValid() {
        MinAgeValidator validator = new MinAgeValidator();
        ReflectionTestUtils.setField(validator, "minAge", 18);

        LocalDate birthDate = LocalDate.now().minusYears(20);
        assertTrue(validator.isValid(birthDate, null));

        birthDate = LocalDate.now().minusYears(17);
        assertFalse(validator.isValid(birthDate, null));
    }
}