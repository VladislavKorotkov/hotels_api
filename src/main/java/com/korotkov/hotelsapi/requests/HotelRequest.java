package com.korotkov.hotelsapi.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRequest {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String brand;

    @NotNull
    private AddressRequest address;

    @NotNull
    private ContactRequest contacts;

    @NotNull
    private ArrivalTimeRequest arrivalTime;
}
