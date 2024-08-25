package com.konkov.CarApp.controllers.handlers;

import com.konkov.CarApp.exception.carExceptions.CarNotCreatedException;
import com.konkov.CarApp.dto.CarStatusResponse;
import com.konkov.CarApp.exception.carExceptions.ModelNotExistException;
import com.konkov.CarApp.exception.carExceptions.NotFoundCarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CarGlobalExceptionHandler {

    //Перехватывает исключение в случае если указанный автомобиль не найден
    @ExceptionHandler
    public ResponseEntity<CarStatusResponse> handleException(NotFoundCarException exception) {
        CarStatusResponse response = new CarStatusResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Перехватывает исключение в случае когда пользователь ввел не валидные данные при добавлении авто
    @ExceptionHandler
    public ResponseEntity<CarStatusResponse> handleException(CarNotCreatedException exception){
        CarStatusResponse response = new CarStatusResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Перехватывает исключение если пользователь передает марку авто, которой нет в nsi_uato_model
    @ExceptionHandler
    public ResponseEntity<CarStatusResponse> handleException(ModelNotExistException exception){
        CarStatusResponse response = new CarStatusResponse();
        response.setMessage(exception.getMessage());
        response.setCode(404);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Перехватывает все исключения со стандартным ответом
    @ExceptionHandler
    public ResponseEntity<CarStatusResponse> handleException(Exception exception) {
        CarStatusResponse response = new CarStatusResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
