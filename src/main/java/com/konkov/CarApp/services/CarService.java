package com.konkov.CarApp.services;

import com.konkov.CarApp.dto.CarDTO;
import com.konkov.CarApp.entity.Car;

import java.util.List;

public interface CarService {
    public List<CarDTO> getAllCars();
    public CarDTO getCarById(int id);
    public void saveCar(Car car);
    List<CarDTO> getFilteredCars(String color, Integer year, String country, Integer numberOfOwner, String model);

}
