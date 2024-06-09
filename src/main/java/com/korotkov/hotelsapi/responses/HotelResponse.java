package com.korotkov.hotelsapi.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Brief information about the hotel")
public class HotelResponse {
    @Schema(description = "Hotel ID", example = "1")
    private Long id;

    @Schema(description = "Hotel name", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "The description of the hotel", example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
    private String description;

    @Schema(description = "Hotel address", example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    private String address;

    @Schema(description = "Phone", example = "+375 17 309-80-00")
    private String phone;
}
