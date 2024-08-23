package com.konkov.CarApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "color")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Цвет должен быть в пределах от 2 до 30 символов")
    private String color;

    @Column(name = "year")
    @Min(value = 1990, message = "Год выпуска должен быть не меньше 1990")
    @Max(value = 2024, message = "Год выпуска должен быть не больше 2024")
    private int year;

    @Column(name = "country")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Страна должна быть в пределах от 2 до 30 символов")
    private String country;

    @Column(name = "number_of_owners")
    @Min(value = 0, message = "Количество владельцев не может быть отрицательным")
    private int numberOfOwners;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private CarModel carModel;

    public Car() {
    }

    public Car(String color, int year, String country, int numberOfOwners, CarModel carModel) {
        this.color = color;
        this.year = year;
        this.country = country;
        this.numberOfOwners = numberOfOwners;
        this.carModel = carModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
