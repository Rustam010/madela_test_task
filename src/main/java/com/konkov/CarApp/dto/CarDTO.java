package com.konkov.CarApp.dto;

import com.konkov.CarApp.entity.CarModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CarDTO {

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Цвет должен быть в пределах от 2 до 30 символов")
    private String color;
    @Min(value = 1990, message = "Год выпуска должен быть не меньше 1990")
    @Max(value = 2024, message = "Год выпуска должен быть не больше 2024")
    private int year;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Страна должна быть в пределах от 2 до 30 символов")
    private String country;

    @Min(value = 0, message = "Количество владельцев не может быть отрицательным")
    private int numberOfOwners;

    private String carModel;

    public CarDTO() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    public void setNumberOfOwners(int numberOfOwners) {
        this.numberOfOwners = numberOfOwners;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
