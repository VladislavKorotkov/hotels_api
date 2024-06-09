package com.korotkov.hotelsapi.utils;

import com.korotkov.hotelsapi.models.*;
import com.korotkov.hotelsapi.requests.*;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(target = "amenities", expression = "java(mapAmenities(hotel.getAmenities()))")
    HotelFullInfoResponse toHotelFullInfoResponse(Hotel hotel);

    List<HotelResponse> toHotelResponseList(List<Hotel> hotels);

    default List<String> mapAmenities(Set<Amenity> amenities) {
        return amenities.stream()
                .map(Amenity::getName)
                .collect(Collectors.toList());
    }
    @Named("addressToString")
    default String addressToString(Address address) {
        return address == null ? null : address.toString(); // Предполагается, что Address имеет корректный метод toString()
    }

    @Mapping(source = "address", target = "address", qualifiedByName = "addressToString")
    @Mapping(source = "contacts.phone", target = "phone")
    HotelResponse toHotelResponse(Hotel hotel);


    @Mapping(source = "address", target = "address")
    @Mapping(source = "contacts", target = "contacts")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    @Mapping(target = "amenities", ignore = true) // Если нужно маппить amenities, добавьте соответствующий маппинг
    Hotel toHotel(HotelRequest hotelRequest);

    // Маппинг для вложенных объектов
    Address toAddress(AddressRequest addressRequest);

    Contact toContact(ContactRequest contactRequest);

    ArrivalTime toArrivalTime(ArrivalTimeRequest arrivalTimeRequest);

}