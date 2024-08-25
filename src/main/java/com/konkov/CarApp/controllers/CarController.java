package com.konkov.CarApp.controllers;

import com.konkov.CarApp.dto.CarDTO;


import com.konkov.CarApp.events.CarAddedEventPublisher;
import com.konkov.CarApp.dto.CarStatusResponse;
import com.konkov.CarApp.services.CarModelService;
import com.konkov.CarApp.services.CarService;
import com.konkov.CarApp.util.MappingUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
                         CarModelService carModelService, CarAddedEventPublisher carAddedEventPublisher) {
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
        return carService.getCarById(id);
    }


    @PostMapping()
    public ResponseEntity<CarStatusResponse> create(@RequestBody @Valid CarDTO carDTO, BindingResult bindingResult) {
        carService.saveCar(carDTO, bindingResult);

        CarStatusResponse carStatusResponse = new CarStatusResponse();
        carStatusResponse.setStatus("Успешно добавлено");

        return new ResponseEntity<>(carStatusResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CarDTO> getFilteredCars(@RequestParam(required = false) String color,
                                        @RequestParam(required = false) Integer year,
                                        @RequestParam(required = false) String country,
                                        @RequestParam(required = false) Integer numberOfOwner,
                                        @RequestParam(required = false) String model) {

        //Возвращает список отфильтрованных машин
        return carService.getFilteredCars(color, year, country, numberOfOwner, model);
    }


}
