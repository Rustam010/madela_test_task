package com.konkov.CarApp.services;

import com.konkov.CarApp.entity.Subscriber;

import java.util.List;

public interface SubscriberService {
    public void subscribe(String email, String model);
    public List<Subscriber> findAllByModel(String model);

}
