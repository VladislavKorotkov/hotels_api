package com.korotkov.hotelsapi.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class AmenitiesRequest {
   // @NotEmpty(message = "Amenities list must not be empty")
    private List<String> amenities;
}
