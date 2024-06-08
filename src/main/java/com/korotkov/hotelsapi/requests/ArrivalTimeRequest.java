package com.korotkov.hotelsapi.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArrivalTimeRequest {
    @NotBlank(message = "The 'checkIn' field cannot be empty")
    private String checkIn;

    private String checkOut;
}
