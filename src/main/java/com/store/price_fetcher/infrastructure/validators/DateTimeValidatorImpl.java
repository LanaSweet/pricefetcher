package com.store.price_fetcher.infrastructure.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidatorImpl implements ConstraintValidator<DateTimeValidator, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public boolean isValid(String dateTime, ConstraintValidatorContext context) {
        if (dateTime == null) {
            return false;
        }
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}