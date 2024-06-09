package com.korotkov.hotelsapi.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArrivalTimeResponse {
    @Schema(description = "The check-in time", example = "14:00")
    private String checkIn;

    @Schema(description = "The check-out time", example = "16:00")
    private String checkOut;
}
