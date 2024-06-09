package com.korotkov.hotelsapi.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description =  "Hotel details to create a new hotel")
public class HotelRequest {
    @Schema(description = "The name of the hotel", example = "DoubleTree by Hilton Minsk", required = true)
    @NotBlank
    private String name;

    @Schema(description = "The description of the hotel", example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
    private String description;

    @Schema(description = "The brand of the hotel", example = "Hilton", required = true)
    @NotBlank
    private String brand;

    @Schema(description = "The address of the hotel", required = true)
    @NotNull
    @Valid
    private AddressRequest address;

    @Schema(description = "The contact information for the hotel", required = true)
    @NotNull
    @Valid
    private ContactRequest contacts;

    @Schema(description = "The arrival and departure times for the hotel stay", required = true)
    @NotNull
    @Valid
    private ArrivalTimeRequest arrivalTime;
}
