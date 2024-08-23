package com.konkov.CarApp.exceptionHandling.carExceptions;

public class CarNotCreatedException extends RuntimeException{
    public CarNotCreatedException(String message) {
        super(message);
    }
}
