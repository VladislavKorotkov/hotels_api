package com.korotkov.hotelsapi.services.impl;

import com.korotkov.hotelsapi.models.Amenity;
import com.korotkov.hotelsapi.repositories.AmenityRepository;
import com.korotkov.hotelsapi.services.AmenityService;
import com.korotkov.hotelsapi.utils.AmenityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;

    @Override
    public List<Amenity> createAmenities(List<String> amenities) {
        Set<Amenity> amenityList = AmenityMapper.INSTANCE.toAmenities(amenities);
        return amenityRepository.saveAll(amenityList);
    }
}
