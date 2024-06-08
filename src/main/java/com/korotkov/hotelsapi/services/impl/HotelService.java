package com.korotkov.hotelsapi.services.impl;

import com.korotkov.hotelsapi.requests.AmenitiesRequest;
import com.korotkov.hotelsapi.requests.HotelRequest;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class HotelService implements com.korotkov.hotelsapi.services.HotelService {
    @Override
    public List<HotelResponse> getAllHotels() {
        return List.of();
    }

    @Override
    public HotelFullInfoResponse getHotelById(Long id) {
        return null;
    }

    @Override
    public List<HotelResponse> searchHotels(String name, String brand, String city, String county, Set<String> amenities) {
        return List.of();
    }

    @Override
    public HotelResponse createHotel(HotelRequest hotelRequest) {
        return null;
    }

    @Override
    public HotelResponse addAmenitiesToHotel(Long hotelId, AmenitiesRequest amenitiesRequest) {
        return null;
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        return Map.of();
    }
}
