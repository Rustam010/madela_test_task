package com.konkov.CarApp.controllers;

import com.konkov.CarApp.dto.CarDTO;
import com.konkov.CarApp.entity.Car;


import com.konkov.CarApp.events.CarAddedEventPublisher;
import com.konkov.CarApp.exceptionHandling.carExceptions.CarNotCreatedException;
import com.konkov.CarApp.exceptionHandling.carExceptions.CarStatusResponse;
import com.konkov.CarApp.exceptionHandling.carExceptions.ModelNotExistException;
import com.konkov.CarApp.services.CarModelService;
import com.konkov.CarApp.services.CarService;
import com.konkov.CarApp.util.MappingUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto/v1/sales")
public class CarController {
    private final MappingUtil mappingUtil;
    private final CarService carService;
    private final CarModelService carModelService;
    private final CarAddedEventPublisher carAddedEventPublisher;

    @Autowired
    public CarController(MappingUtil mappingUtil, CarService carService,
                         CarModelService carModelService,CarAddedEventPublisher carAddedEventPublisher) {
        this.mappingUtil = mappingUtil;
        this.carService = carService;
        this.carModelService = carModelService;
        this.carAddedEventPublisher = carAddedEventPublisher;
    }


    @GetMapping("/all")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable int id) {
        CarDTO carDTO = carService.getCarById(id);
        return carDTO;
    }



    //В случае успешного добавления возвращается CarStatusResponse
    @PostMapping()
    public ResponseEntity<CarStatusResponse> create(@RequestBody @Valid CarDTO carDTO, BindingResult bindingResult) {

        //Проверка валидности всех полей
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ")
                                .append(error.getDefaultMessage()).append(";");
            }
            throw new CarNotCreatedException(errorMessage.toString());
        }

        //Проверка, существует ли передаваемая марка автомобиля в nsi_auto_model
        if (!carModelService.isCarModelExists(carDTO.getCarModel())){
            throw new ModelNotExistException("Марка авто отсутствует");
        }

        Car car = mappingUtil.mapToCar(carDTO);
        carService.saveCar(car);

        //Добавляет event при создании нового автомобиля
        carAddedEventPublisher.publishCarAddedEvent(car);

        CarStatusResponse carStatusResponse = new CarStatusResponse();
        carStatusResponse.setStatus("Успешно добавлено");
        
        return new ResponseEntity<>(carStatusResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CarDTO> getCarsByKeys(@RequestParam(required = false) String color,
                                      @RequestParam(required = false) Integer year,
                                      @RequestParam(required = false) String country,
                                      @RequestParam(required = false) Integer numberOfOwner,
                                      @RequestParam(required = false) String model) {

        //Возвращает список отфильтрованных машин
        List<CarDTO> cars = carService.getFilteredCars(color, year, country, numberOfOwner, model);
        return cars;
    }


}
