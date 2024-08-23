package com.konkov.CarApp.repositories;

import com.konkov.CarApp.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    CarModel findByModel(String model); // Возвращает объект CarModel по его строке model
    boolean existsByModel(String model); //Проверяет по полю model, существует ли такой объект
}
