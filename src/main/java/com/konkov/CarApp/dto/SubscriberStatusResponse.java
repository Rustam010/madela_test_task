package com.konkov.CarApp.dto;

public class SubscriberStatusResponse {
    private String message;

    public SubscriberStatusResponse() {
    }

    public SubscriberStatusResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
