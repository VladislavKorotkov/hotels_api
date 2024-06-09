package com.korotkov.hotelsapi;

import com.korotkov.hotelsapi.exceptions.IncorrectParameterException;
import com.korotkov.hotelsapi.exceptions.ObjectNotFoundException;
import com.korotkov.hotelsapi.models.Address;
import com.korotkov.hotelsapi.models.Amenity;
import com.korotkov.hotelsapi.models.Hotel;
import com.korotkov.hotelsapi.repositories.HotelRepository;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import com.korotkov.hotelsapi.services.impl.HotelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    @DisplayName("Test getting all hotels")
    public void testGetAllHotels() {
        List<Hotel> hotels = Collections.singletonList(new Hotel(1L, "Hotel A", "Description", "Brand A", null, null, null, null));
        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);
        List<HotelResponse> result = hotelService.getAllHotels();

        assertEquals(1, result.size());
        assertEquals("Hotel A", result.get(0).getName());
    }

    @Test
    @DisplayName("Test getting hotel by ID")
    public void testGetHotelById() {
        Hotel hotel = new Hotel(1L, "Hotel A", "Description", "Brand A", null, null, null, Set.of(new Amenity()));
        Mockito.when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        HotelFullInfoResponse result = hotelService.getHotelById(1L);
        assertEquals("Brand A", result.getBrand());
    }

    @Test
    @DisplayName("Test getting hotel by ID when not found")
    public void testGetHotelByIdNotFound() {
        Mockito.when(hotelRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> hotelService.getHotelById(1L));
    }


    @Test
    @DisplayName("Test searching for hotels by name")
    public void testSearchHotelsByName() {
        List<Hotel> hotels = List.of(
                new Hotel(1L, "Hotel A", "Description", "Brand A", new Address(1, "Yakuba Kolasa", "Minsk","Belarus","123112"), null, null ,null),
                new Hotel(2L, "Hotel B", "Description", "Brand B", new Address(2, "Yakuba Kolasa", "Minsk","Belarus","123112"), null, null, null),
                new Hotel(3L, "Hotel C", "Description", "Brand C", new Address(3, "Yakuba Kolasa", "Moscow","Russia","123112"), null, null, null));
        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);

        List<HotelResponse> result = hotelService.searchHotels("Hotel A", null, null, null, null);

        assertEquals(1, result.size());
        assertEquals("Hotel A", result.get(0).getName());
    }

    @Test
    @DisplayName("Test search hotels by brand")
    public void testSearchHotelsWithBrand() {
        List<Hotel> hotels = List.of(
                new Hotel(1L, "Hotel A", "Description", "Brand A", new Address(1, "Yakuba Kolasa", "Minsk","Belarus","123112"), null, null ,null),
                new Hotel(2L, "Hotel B", "Description", "Brand B", new Address(2, "Yakuba Kolasa", "Minsk","Belarus","123112"), null, null, null),
                new Hotel(3L, "Hotel C", "Description", "Brand C", new Address(3, "Yakuba Kolasa", "Moscow","Russia","123112"), null, null, null));
        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);

        List<HotelResponse> result = hotelService.searchHotels(null, "Brand A", null, null, null);

        assertEquals(1, result.size());
        assertEquals("Hotel A", result.get(0).getName());
    }

    @Test
    @DisplayName("Test search hotels by city")
    public void testSearchHotelsWithCity() {
        List<Hotel> hotels = List.of(
                new Hotel(1L, "Hotel A", "Description", "Brand A", new Address(1, "Yakuba Kolasa", "Minsk","Belarus","123112"), null, null ,null),
                new Hotel(2L, "Hotel B", "Description", "Brand B", new Address(2, "Yakuba Kolasa", "Minsk","Belarus","123112"), null, null, null),
                new Hotel(3L, "Hotel C", "Description", "Brand C", new Address(3, "Yakuba Kolasa", "Moscow","Russia","123112"), null, null, null));
        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);

        List<HotelResponse> result = hotelService.searchHotels(null, null, "Minsk", null, null);

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test get histogram by brand")
    public void testGetHistogram() {
        List<Hotel> hotels = List.of(
                new Hotel(1L, "Hotel A", "Description", "Brand A", null, null, null, null),
                new Hotel(2L, "Hotel B", "Description", "Brand A", null, null, null, null),
                new Hotel(3L, "Hotel C", "Description", "Brand B", null, null, null, null)
        );
        Mockito.when(hotelRepository.findAll()).thenReturn(hotels);

        Map<String, Long> histogram = hotelService.getHistogram("brand");

        assertEquals(2, histogram.get("Brand A"));
        assertEquals(1, histogram.get("Brand B"));
    }

    @Test
    @DisplayName("Test get histogram with invalid parameter")
    public void testGetHistogramInvalidParam() {
        List<Hotel> hotels = List.of(
                new Hotel(1L, "Hotel A", "Description", "Brand A", null, null, null, null),
                new Hotel(2L, "Hotel B", "Description", "Brand A", null, null, null, null),
                new Hotel(3L, "Hotel C", "Description", "Brand B", null, null, null, null)
        );
        Mockito.lenient().when(hotelRepository.findAll()).thenReturn(hotels);

        assertThrows(IncorrectParameterException.class, () -> hotelService.getHistogram("invalid"));
    }
}