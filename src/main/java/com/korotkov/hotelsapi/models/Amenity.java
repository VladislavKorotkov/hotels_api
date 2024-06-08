package com.korotkov.hotelsapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "amenity")
@Data
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}