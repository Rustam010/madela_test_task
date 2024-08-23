package com.konkov.CarApp.exceptionHandling.carExceptions;

public class ModelNotExistException extends RuntimeException{
    public ModelNotExistException(String message) {
        super(message);
    }
}
