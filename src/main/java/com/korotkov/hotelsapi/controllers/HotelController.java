package com.korotkov.hotelsapi.controllers;

import com.korotkov.hotelsapi.models.Hotel;
import com.korotkov.hotelsapi.requests.AmenitiesRequest;
import com.korotkov.hotelsapi.requests.HotelRequest;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import com.korotkov.hotelsapi.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/property-view")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelFullInfoResponse> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest hotelRequest) {
        return ResponseEntity.ok(hotelService.createHotel(hotelRequest));
    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<HotelResponse> addAmenitiesToHotel(@PathVariable Long id, @RequestBody AmenitiesRequest amenitiesRequest) {
        return ResponseEntity.ok(hotelService.addAmenitiesToHotel(id,amenitiesRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelResponse>> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String county,
            @RequestParam(required = false) Set<String> amenities) {
        return ResponseEntity.ok(hotelService.searchHotels(name, brand, city, county, amenities));
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<?> getHistogram(@PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHistogram(param));
    }
}