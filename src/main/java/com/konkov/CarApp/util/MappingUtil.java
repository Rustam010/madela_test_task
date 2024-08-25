package com.konkov.CarApp.util;

import com.konkov.CarApp.dto.CarDTO;
import com.konkov.CarApp.dto.SubscriberDTO;
import com.konkov.CarApp.entity.Car;
import com.konkov.CarApp.entity.CarModel;
import com.konkov.CarApp.entity.Subscriber;
import com.konkov.CarApp.services.CarModelService;
import org.springframework.stereotype.Component;

@Component
public class MappingUtil {

    private final CarModelService carModelService;

    public MappingUtil(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    //Мапим из car в carDTO
    public CarDTO mapToCarDTO(Car car){
        CarDTO carDTO = new CarDTO();

        carDTO.setColor(car.getColor());
        carDTO.setYear(car.getYear());
        carDTO.setCountry(car.getCountry());
        carDTO.setNumberOfOwners(car.getNumberOfOwners());
        carDTO.setCarModel(car.getCarModel().getModel());

        return carDTO;
    }


    public Car mapToCar(CarDTO carDTO){
        Car car = new Car();

        car.setColor(carDTO.getColor());
        car.setYear(carDTO.getYear());
        car.setCountry(carDTO.getCountry());
        car.setNumberOfOwners(carDTO.getNumberOfOwners());
        car.setCarModel(carModelService.findByModel(carDTO.getCarModel()));

        return car;
    }

    public SubscriberDTO mapToSubscriberDTO(Subscriber subscriber){
        SubscriberDTO subscriberDTO = new SubscriberDTO();

        subscriberDTO.setEmail(subscriber.getEmail());
        subscriberDTO.setModel(subscriber.getModel());
        return subscriberDTO;
    }

    public Subscriber mapToSubscriber(SubscriberDTO subscriberDTO){
        Subscriber subscriber = new Subscriber();

        subscriber.setModel(subscriberDTO.getModel());
        subscriber.setEmail(subscriberDTO.getEmail());
        return subscriber;
    }
}
