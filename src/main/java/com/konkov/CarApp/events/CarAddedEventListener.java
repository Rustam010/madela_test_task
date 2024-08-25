package com.konkov.CarApp.events;

import com.konkov.CarApp.dto.SubscriberDTO;
import com.konkov.CarApp.entity.Car;
import com.konkov.CarApp.entity.Subscriber;
import com.konkov.CarApp.services.MailService;
import com.konkov.CarApp.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CarAddedEventListener {

    private final SubscriberService subscriberService;
    private final MailService emailService;

    @Autowired
    public CarAddedEventListener(SubscriberService subscriberService, MailService emailService) {
        this.subscriberService = subscriberService;
        this.emailService = emailService;
    }

    @EventListener
    public void handleCarAddedEvent(CarAddedEvent event) {
        Car car = event.getCar();
        List<SubscriberDTO> subscribers = subscriberService.findAllByModel(car.getCarModel().getModel());

        for (SubscriberDTO subscriber : subscribers) {
            String subject = "Новый автомобиль в продаже!";
            String text = "Автомобиль " + car.getCarModel().getModel() + " поступил в продажу";
            emailService.sendEmail(subscriber.getEmail(), subject, text);
        }
    }
}