package com.konkov.CarApp.exceptionHandling.subscriberExceptions;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException(String message) {
        super(message);
    }
}
