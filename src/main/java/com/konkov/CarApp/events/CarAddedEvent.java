package com.konkov.CarApp.events;

import com.konkov.CarApp.entity.Car;
import org.springframework.context.ApplicationEvent;

public class CarAddedEvent extends ApplicationEvent {

    private final Car car;

    public CarAddedEvent(Object source, Car car) {
        super(source);
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}