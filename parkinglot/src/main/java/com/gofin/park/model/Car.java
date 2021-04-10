package com.gofin.park.model;

import java.io.Serializable;

public final class Car implements Serializable {

    private static final long serialVersionUID=1L;

    private final String registrationNo;
    private final String colour;

    public Car(String registrationNo, String colour) {
        this.registrationNo = registrationNo;
        this.colour = colour;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNo='" + registrationNo + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
