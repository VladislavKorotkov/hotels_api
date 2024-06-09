package com.korotkov.hotelsapi.services;

import com.korotkov.hotelsapi.models.Amenity;

import java.util.List;

public interface AmenityService {
    List<Amenity> createAmenities(List<String> amenities);
}
