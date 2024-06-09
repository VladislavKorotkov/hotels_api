package com.korotkov.hotelsapi.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {

    @Schema(description = "The house number", example = "9", required = true)
    @NotNull(message = "The 'houseNumber' field cannot be empty")
    private int houseNumber;

    @Schema(description = "The street name", example = "Pobediteley Avenue", required = true)
    @NotBlank(message = "The 'street' field cannot be empty")
    private String street;

    @Schema(description = "The city name", example = "Minsk", required = true)
    @NotBlank(message = "The 'city' field cannot be empty")
    private String city;

    @Schema(description = "The county name", example = "Belarus", required = true)
    @NotBlank(message = "The 'county' field cannot be empty")
    private String county;

    @Schema(description = "The postal code", example = "220004", required = true)
    @NotBlank(message = "The 'postCode' field cannot be empty")
    private String postCode;
}
