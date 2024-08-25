package com.konkov.CarApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SubscriberDTO {
    @Email(message = "Введен некорректный email")
    private String email;

    @NotEmpty(message = "Поле не должно быть пустым")
    private String model;

    public SubscriberDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
