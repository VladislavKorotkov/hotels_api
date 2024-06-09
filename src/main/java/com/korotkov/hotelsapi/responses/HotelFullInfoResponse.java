package com.korotkov.hotelsapi.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Full information about the hotel")
public class HotelFullInfoResponse {
    @Schema(description = "Hotel ID", example = "1")
    private Long id;

    @Schema(description = "Hotel name", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Hotel brand", example = "Hilton")
    private String brand;

    @Schema(description = "Hotel address")
    private AddressResponse address;

    @Schema(description = "Hotel contact information")
    private ContactResponse contacts;

    @Schema(description = "Hotel arrival time")
    private ArrivalTimeResponse arrivalTime;

    @Schema(description = "List of hotel amenities", example = "[\"Free parking\", \"Free WiFi\", \"Non-smoking rooms\"]")
    private List<String> amenities;
}
