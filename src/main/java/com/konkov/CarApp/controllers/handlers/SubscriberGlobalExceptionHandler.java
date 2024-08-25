package com.konkov.CarApp.controllers.handlers;

import com.konkov.CarApp.exception.carExceptions.ModelNotExistException;
import com.konkov.CarApp.exception.subscriberExceptions.NotUniqueEmailException;
import com.konkov.CarApp.dto.SubscriberStatusResponse;
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

    @ExceptionHandler
    public ResponseEntity<SubscriberStatusResponse> handleException(ModelNotExistException exception){
        SubscriberStatusResponse response = new SubscriberStatusResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
