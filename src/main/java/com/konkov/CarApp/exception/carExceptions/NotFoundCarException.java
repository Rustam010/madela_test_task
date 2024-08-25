package com.konkov.CarApp.exception.carExceptions;

public class NotFoundCarException extends RuntimeException{
    public NotFoundCarException(String message) {
        super(message);
    }
}
