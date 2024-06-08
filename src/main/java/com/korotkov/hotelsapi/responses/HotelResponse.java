package com.korotkov.hotelsapi.responses;

import lombok.Data;

@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
