package com.korotkov.hotelsapi.responses;

import com.korotkov.hotelsapi.models.Address;
import com.korotkov.hotelsapi.models.ArrivalTime;
import com.korotkov.hotelsapi.models.Contact;
import lombok.Data;

import java.util.List;

@Data
public class HotelFullInfoResponse {
    private Long id;
    private String name;
    private String brand;
    private Address address;
    private Contact contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;
}
