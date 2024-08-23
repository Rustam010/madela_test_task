package com.konkov.CarApp.controllers;

import com.konkov.CarApp.services.SubscriberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auto/v1/subscribe")
public class SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    public ResponseEntity<String> subscribe(@RequestParam String email, @RequestParam String model){
        subscriberService.subscribe(email,model);
        return ResponseEntity.ok("Пользователь " + email + " успешно подписался на модель " + model);
    }
}
