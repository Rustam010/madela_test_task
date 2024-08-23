package com.konkov.CarApp.exceptionHandling.subscriberExceptions;

import com.konkov.CarApp.entity.Subscriber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SubscriberGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SubscriberStatusResponse> handleException(NotUniqueEmailException exception){
        SubscriberStatusResponse response = new SubscriberStatusResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
