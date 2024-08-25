package com.konkov.CarApp.exception.subscriberExceptions;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException(String message) {
        super(message);
    }
}
