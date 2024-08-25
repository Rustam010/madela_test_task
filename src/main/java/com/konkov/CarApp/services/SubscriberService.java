package com.konkov.CarApp.services;

import com.konkov.CarApp.dto.SubscriberDTO;
import com.konkov.CarApp.entity.Subscriber;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface SubscriberService {
    public void subscribe(SubscriberDTO subscriberDTO, BindingResult bindingResult);
    public List<SubscriberDTO> findAllByModel(String model);

}
