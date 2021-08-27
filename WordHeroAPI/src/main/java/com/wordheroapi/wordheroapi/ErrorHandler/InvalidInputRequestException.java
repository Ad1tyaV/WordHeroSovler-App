package com.wordheroapi.wordheroapi.ErrorHandler;



public class InvalidInputRequestException extends RuntimeException{


    public InvalidInputRequestException(String message){
        super(message);
    }

    public InvalidInputRequestException(String message, Throwable cause){
        super(message, cause);
    }

}