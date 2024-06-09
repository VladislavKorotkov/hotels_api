package com.korotkov.hotelsapi.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArrivalTimeRequest {
    @Schema(description = "The check-in time", example = "14:00", required = true)
    @NotBlank(message = "The 'checkIn' field cannot be empty")
    private String checkIn;

    @Schema(description = "The check-out time", example = "16:00")
    private String checkOut;
}
