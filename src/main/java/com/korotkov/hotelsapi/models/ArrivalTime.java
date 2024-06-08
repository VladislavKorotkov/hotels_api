package com.korotkov.hotelsapi.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ArrivalTime {
    private String checkIn;
    private String checkOut;
}
