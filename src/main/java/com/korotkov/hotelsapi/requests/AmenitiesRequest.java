package com.korotkov.hotelsapi.requests;

import lombok.Data;

import java.util.List;

@Data
public class AmenitiesRequest {
    private List<String> amenities;
}
