package com.konkov.CarApp.exception.carExceptions;

public class ModelNotExistException extends RuntimeException{
    public ModelNotExistException(String message) {
        super(message);
    }
}
