package com.korotkov.hotelsapi.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {
    @NotNull(message = "The 'houseNumber' field cannot be empty")
    private int houseNumber;
    @NotBlank(message = "The 'street' field cannot be empty")
    private String street;
    @NotBlank(message = "The 'city' field cannot be empty")
    private String city;
    @NotBlank(message = "The 'county' field cannot be empty")
    private String county;
    @NotBlank(message = "The 'postCode' field cannot be empty")
    private String postCode;
}
