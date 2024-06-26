package com.korotkov.hotelsapi.services;

import com.korotkov.hotelsapi.models.Hotel;
import com.korotkov.hotelsapi.requests.HotelRequest;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HotelService {
    List<HotelResponse> getAllHotels();
    HotelFullInfoResponse getHotelById(Long id);
    List<HotelResponse> searchHotels(String name, String brand, String city, String county, Set<String> amenities);
    HotelResponse createHotel(HotelRequest hotelRequest);
    HotelFullInfoResponse addAmenitiesToHotel(Long hotelId, List<String> amenities);
    Map<String, Long> getHistogram(String param);
    Hotel getHotel(Long id);
}
