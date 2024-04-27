package com.example.clearsolutiontask.advices;

import com.example.clearsolutiontask.exceptions.UserException;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionAdviceTest {
    @Test
    public void testHandleValidationExceptions() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

        GlobalExceptionAdvice advice = new GlobalExceptionAdvice();
        Map<String, String> errors = advice.handleValidationExceptions(ex);

        assertEquals(1, errors.size());
        assertEquals("defaultMessage", errors.get("field"));
    }

    @Test
    public void testHandleDateTimeParseException() {
        String errorMessage = "Text '2020-13-01' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13";
        DateTimeParseException ex = new DateTimeParseException(errorMessage, "2020-13-01", 5);

        GlobalExceptionAdvice advice = new GlobalExceptionAdvice();
        Map<String, String> errors = advice.handleDateTimeParseException(ex);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get("error"));
    }

    @Test
    public void testHandleUserExceptionException() {
        String errorMessage = "User with id 1 does not exist";
        UserException ex = new UserException(errorMessage);

        GlobalExceptionAdvice advice = new GlobalExceptionAdvice();
        Map<String, String> errors = advice.handleUserExceptionException(ex);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get("error"));
    }

    @Test
    public void testHandleAllOtherExceptions() {
        String errorMessage = "Unexpected error occurred";
        Exception ex = new Exception(errorMessage);

        GlobalExceptionAdvice advice = new GlobalExceptionAdvice();
        Map<String, String> errors = advice.handleAllOtherExceptions(ex);

        assertEquals(1, errors.size());
        assertEquals(errorMessage, errors.get("error"));
    }
}
