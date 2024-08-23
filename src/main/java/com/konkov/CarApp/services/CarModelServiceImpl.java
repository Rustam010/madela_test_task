package com.konkov.CarApp.services;

import com.konkov.CarApp.entity.Car;
import com.konkov.CarApp.entity.CarModel;
import com.konkov.CarApp.repositories.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    @Autowired
    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CarModel findByModel(String model) {
        return carModelRepository.findByModel(model);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCarModelExists(String model) {
        return carModelRepository.existsByModel(model);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarModel> getAllModels() {
        return carModelRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CarModel getModelById(int id) {
        return carModelRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void saveModel(CarModel model) {
        carModelRepository.save(model);
    }

}
