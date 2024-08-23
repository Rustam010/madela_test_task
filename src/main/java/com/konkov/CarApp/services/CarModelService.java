package com.konkov.CarApp.services;


import com.konkov.CarApp.entity.Car;
import com.konkov.CarApp.entity.CarModel;

import java.util.List;

public interface CarModelService {
    public List<CarModel> getAllModels();
    public CarModel getModelById(int id);
    public void saveModel(CarModel model);
    public CarModel findByModel(String model);

    boolean isCarModelExists(String model);
}
