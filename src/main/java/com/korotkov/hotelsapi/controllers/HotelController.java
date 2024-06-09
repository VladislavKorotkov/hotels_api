package com.korotkov.hotelsapi.controllers;

import com.korotkov.hotelsapi.requests.AmenitiesRequest;
import com.korotkov.hotelsapi.requests.HotelRequest;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import com.korotkov.hotelsapi.services.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/property-view")
@AllArgsConstructor
@Validated
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponse> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HotelFullInfoResponse getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse createHotel(@Valid @RequestBody HotelRequest hotelRequest) {
        return hotelService.createHotel(hotelRequest);
    }

    @PostMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse addAmenitiesToHotel(@PathVariable Long id,@RequestBody List<String> amenities) {
        return hotelService.addAmenitiesToHotel(id, amenities);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponse> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String county,
            @RequestParam(required = false) Set<String> amenities) {
        return hotelService.searchHotels(name, brand, city, county, amenities);
    }

    @GetMapping("/histogram/{param}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogram(param);
    }
}