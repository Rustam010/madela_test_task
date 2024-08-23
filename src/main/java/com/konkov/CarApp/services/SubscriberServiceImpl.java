package com.konkov.CarApp.services;

import com.konkov.CarApp.entity.Subscriber;
import com.konkov.CarApp.exceptionHandling.subscriberExceptions.NotUniqueEmailException;
import com.konkov.CarApp.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public void subscribe(String email, String model){
        if (subscriberRepository.existsByEmailAndModel(email, model)){
            throw new NotUniqueEmailException(email + " уже подписан на модель:" + model);
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);
        subscriber.setModel(model);
        subscriberRepository.save(subscriber);
    }

    @Override
    public List<Subscriber> findAllByModel(String model) {
        return subscriberRepository.findAllByModel(model);
    }
}
