package com.konkov.CarApp.events;

import com.konkov.CarApp.entity.Car;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CarAddedEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public CarAddedEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishCarAddedEvent(Car car) {
        CarAddedEvent carAddedEvent = new CarAddedEvent(this, car);
        eventPublisher.publishEvent(carAddedEvent);
    }
}