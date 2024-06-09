package com.korotkov.hotelsapi.services.impl;

import com.korotkov.hotelsapi.exceptions.IncorrectParameter;
import com.korotkov.hotelsapi.exceptions.ObjectNotFoundException;
import com.korotkov.hotelsapi.models.Amenity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<Hotel> hotels = hotelRepository.findAll().stream()
                .filter(hotel -> (name == null || hotel.getName().equalsIgnoreCase(name)) &&
                        (brand == null || hotel.getBrand().equalsIgnoreCase(brand)) &&
                        (city == null || hotel.getAddress().getCity().equalsIgnoreCase(city)) &&
                        (county == null || hotel.getAddress().getCounty().equalsIgnoreCase(county)) &&
                        (amenities == null || hotel.getAmenities().stream().map(Amenity::getName).collect(Collectors.toSet()).containsAll(amenities)))
                .collect(Collectors.toList());
        return HotelMapper.INSTANCE.toHotelResponseList(hotels);
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
    public HotelFullInfoResponse addAmenitiesToHotel(Long hotelId, List<String> amenities) {
        Hotel hotel = getHotel(hotelId);
        hotel.getAmenities().addAll(amenityService.createAmenities(amenities));
        hotel = hotelRepository.save(hotel);
        return HotelMapper.INSTANCE.toHotelFullInfoResponse(hotel);
    }

    public Map<String, Long> getHistogram(String param) {
        Map<String, Long> histogram = new HashMap<>();
        switch (param) {
            case "brand":
                histogram = hotelRepository.findAll().stream()
                        .collect(Collectors.groupingBy(Hotel::getBrand, Collectors.counting()));
                break;
            case "city":
                histogram = hotelRepository.findAll().stream()
                        .collect(Collectors.groupingBy(hotel -> hotel.getAddress().getCity(), Collectors.counting()));
                break;
            case "county":
                histogram = hotelRepository.findAll().stream()
                        .collect(Collectors.groupingBy(hotel -> hotel.getAddress().getCounty(), Collectors.counting()));
                break;
            case "amenities":
                List<Hotel> allHotels = hotelRepository.findAll();
                Map<String, Long> amenityCount = new HashMap<>();
                for (Hotel hotel : allHotels) {
                    for (Amenity amenity : hotel.getAmenities()) {
                        amenityCount.put(amenity.getName(),
                                amenityCount.getOrDefault(amenity.getName(), 0L) + 1);
                    }
                }
                histogram = amenityCount;
                break;
            default:
                throw new IncorrectParameter("Invalid parameter for histogram: " + param);
        }
        return histogram;
    }

    @Override
    public Hotel getHotel(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ObjectNotFoundException("The hotel was not found by id"));
    }
}
