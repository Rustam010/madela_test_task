package com.konkov.CarApp.services;

import com.konkov.CarApp.dto.CarDTO;
import com.konkov.CarApp.entity.Car;
import com.konkov.CarApp.events.CarAddedEventPublisher;
import com.konkov.CarApp.exception.carExceptions.CarNotCreatedException;
import com.konkov.CarApp.exception.carExceptions.ModelNotExistException;
import com.konkov.CarApp.exception.carExceptions.NotFoundCarException;
import com.konkov.CarApp.repositories.CarModelRepository;
import com.konkov.CarApp.repositories.CarRepository;
import com.konkov.CarApp.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MappingUtil mappingUtil;
    private final CarAddedEventPublisher carAddedEventPublisher;
    private final CarModelRepository carModelRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, MappingUtil mappingUtil,
                          CarAddedEventPublisher carAddedEventPublisher, CarModelRepository carModelRepository) {
        this.carRepository = carRepository;
        this.mappingUtil = mappingUtil;
        this.carAddedEventPublisher = carAddedEventPublisher;
        this.carModelRepository = carModelRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : cars) {
            CarDTO carDTO = mappingUtil.mapToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Transactional(readOnly = true)
    @Override
    public CarDTO getCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundCarException("Автомобиль с id=" + id + " не найден!"));
        return mappingUtil.mapToCarDTO(car);
    }


    @Transactional
    @Override
    public void saveCar(CarDTO carDTO, BindingResult bindingResult) {

        validCheck(carDTO, bindingResult);

        Car car = mappingUtil.mapToCar(carDTO);
        carRepository.save(car);

        // Публикуем событие после сохранения автомобиля
        carAddedEventPublisher.publishCarAddedEvent(car);
    }


    private void validCheck(CarDTO carDTO, BindingResult bindingResult) {
        //Проверка валидности всех полей
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }
            throw new CarNotCreatedException(errorMessage.toString());
        }

        //Проверка, существует ли передаваемая марка автомобиля в nsi_auto_model
        if (!carModelRepository.existsByModel(carDTO.getCarModel())) {
            throw new ModelNotExistException("Марка авто отсутствует");
        }
    }


    @Transactional(readOnly = true)
    @Override
    public List<CarDTO> getFilteredCars(String color, Integer year, String country, Integer numberOfOwners, String model) {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> filteredCars = cars.stream()
                .filter(car -> (color == null || color.isEmpty() || color.equalsIgnoreCase(car.getColor())))
                .filter(car -> (year == null || year.equals(car.getYear())))
                .filter(car -> (country == null || country.isEmpty() || country.equalsIgnoreCase(car.getCountry())))
                .filter(car -> (numberOfOwners == null || numberOfOwners.equals(car.getNumberOfOwners())))
                .filter(car -> (model == null || model.isEmpty() || model.equalsIgnoreCase(car.getCarModel().getModel())))
                .map(mappingUtil::mapToCarDTO)
                .collect(Collectors.toList());

        if (filteredCars.isEmpty()) {
            throw new NotFoundCarException("По данным фильтрам не найдено ни одного автомобиля");
        }

        return filteredCars;
    }

}
