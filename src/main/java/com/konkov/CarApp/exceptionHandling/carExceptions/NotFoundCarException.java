package com.konkov.CarApp.exceptionHandling.carExceptions;

public class NotFoundCarException extends RuntimeException{
    public NotFoundCarException(String message) {
        super(message);
    }
}
