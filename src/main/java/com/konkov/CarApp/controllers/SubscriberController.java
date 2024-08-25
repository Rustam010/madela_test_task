package com.konkov.CarApp.controllers;

import com.konkov.CarApp.dto.SubscriberDTO;
import com.konkov.CarApp.dto.SubscriberStatusResponse;
import com.konkov.CarApp.services.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auto/v1/subscribe")
public class SubscriberController {
    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    public ResponseEntity<SubscriberStatusResponse> subscribe(@RequestBody @Valid SubscriberDTO subscriberDTO, BindingResult bindingResult) {
        subscriberService.subscribe(subscriberDTO, bindingResult);

        SubscriberStatusResponse response = new SubscriberStatusResponse();
        response.setMessage("Пользователь " + subscriberDTO.getEmail() +
                                              " успешно подписался на модель " + subscriberDTO.getModel());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
