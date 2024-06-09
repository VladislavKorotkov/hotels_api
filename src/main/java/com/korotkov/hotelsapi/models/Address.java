package com.korotkov.hotelsapi.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private int houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;

    @Override
    public String toString() {
        return houseNumber +
                " " + street +
                ", " + city +
                ", " + county +
                ", " + postCode;
    }
}