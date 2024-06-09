package com.korotkov.hotelsapi.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddressResponse {

    @Schema(description = "The house number", example = "9")
    private int houseNumber;
    @Schema(description = "The street name", example = "Pobediteley Avenue")
    private String street;

    @Schema(description = "The city name", example = "Minsk")
    private String city;

    @Schema(description = "The county name", example = "Belarus")
    private String county;

    @Schema(description = "The postal code", example = "220004")
    private String postCode;
}
