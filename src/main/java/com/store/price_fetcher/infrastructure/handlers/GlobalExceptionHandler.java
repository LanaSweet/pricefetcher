package com.store.price_fetcher.infrastructure.handlers;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<String> handleDateTimeParseException(DateTimeParseException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Invalid date format. Expected format is yyyy-MM-dd'T'HH:mm:ss");
	}

}