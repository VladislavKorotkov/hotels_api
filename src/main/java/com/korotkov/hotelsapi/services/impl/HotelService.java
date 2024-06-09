package com.korotkov.hotelsapi.services.impl;

import com.korotkov.hotelsapi.exceptions.ObjectNotFoundException;
import com.korotkov.hotelsapi.models.Hotel;
import com.korotkov.hotelsapi.repositories.HotelRepository;
import com.korotkov.hotelsapi.requests.HotelRequest;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import com.korotkov.hotelsapi.services.AmenityService;
import com.korotkov.hotelsapi.utils.HotelMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class HotelService implements com.korotkov.hotelsapi.services.HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityService amenityService;

    @Override
    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return HotelMapper.INSTANCE.toHotelResponseList(hotels);
    }

    @Override
    public HotelFullInfoResponse getHotelById(Long id) {
        Hotel hotel = getHotel(id);
        return HotelMapper.INSTANCE.toHotelFullInfoResponse(hotel);
    }

    @Override
    public List<HotelResponse> searchHotels(String name, String brand, String city, String county, Set<String> amenities) {
        return List.of();
    }

    @Override
    @Transactional
    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel hotel = HotelMapper.INSTANCE.toHotel(hotelRequest);
        hotel = hotelRepository.save(hotel);
        return HotelMapper.INSTANCE.toHotelResponse(hotel);
    }

    @Override
    @Transactional
    public HotelResponse addAmenitiesToHotel(Long hotelId, List<String> amenities) {
        Hotel hotel = getHotel(hotelId);
        hotel.getAmenities().addAll(amenityService.createAmenities(amenities));
        hotel = hotelRepository.save(hotel);
        return HotelMapper.INSTANCE.toHotelResponse(hotel);
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        return Map.of();
    }

    @Override
    public Hotel getHotel(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ObjectNotFoundException("The hotel was not found by id"));
    }
}
