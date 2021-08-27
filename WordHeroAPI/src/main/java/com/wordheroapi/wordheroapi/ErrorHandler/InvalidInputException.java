package com.wordheroapi.wordheroapi.ErrorHandler;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class InvalidInputException{
    
    private final String message;
    // private final Throwable cause;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public InvalidInputException(String message,  HttpStatus httpStatus, ZonedDateTime timestamp){

        this.message = message;
        // this.cause = cause;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;

    }

    public String getMessage(){
        return message;
    }

    // public Throwable getCause(){
    //     return cause;
    // }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public ZonedDateTime getTimestamp(){
        return timestamp;
    }

}
