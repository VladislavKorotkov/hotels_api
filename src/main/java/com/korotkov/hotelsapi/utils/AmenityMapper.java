package com.korotkov.hotelsapi.utils;

import com.korotkov.hotelsapi.models.Amenity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface AmenityMapper {

    AmenityMapper INSTANCE = Mappers.getMapper(AmenityMapper.class);

    default Set<Amenity> toAmenities(List<String> amenities) {
        return amenities.stream()
                .map(name -> {
                    Amenity amenity = new Amenity();
                    amenity.setName(name);
                    return amenity;
                })
                .collect(Collectors.toSet());
    }
}
