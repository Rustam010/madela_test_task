package com.konkov.CarApp.exceptionHandling.subscriberExceptions;

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
