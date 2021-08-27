package com.wordheroapi.wordheroapi.ErrorHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidInputExceptionHandler {
    
    @ExceptionHandler(value={InvalidInputRequestException.class})
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputRequestException invalidInputRequestException){

        // InvalidInputException invalidInputException = new InvalidInputException(invalidInputRequestException.getMessage(), invalidInputRequestException, HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        InvalidInputException invalidInputException = new InvalidInputException(invalidInputRequestException.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(invalidInputException, HttpStatus.BAD_REQUEST);

    }


}
