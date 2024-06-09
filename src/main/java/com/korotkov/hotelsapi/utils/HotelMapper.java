package com.korotkov.hotelsapi.utils;

import com.korotkov.hotelsapi.models.*;
import com.korotkov.hotelsapi.requests.*;
import com.korotkov.hotelsapi.responses.*;
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
    @Mapping(source = "address", target = "address", qualifiedByName = "addressToAddressResponse")
    @Mapping(source = "contacts", target = "contacts", qualifiedByName = "contactToContactResponse")
    @Mapping(source = "arrivalTime", target = "arrivalTime", qualifiedByName = "arrivalTimeToArrivalTimeResponse")
    HotelFullInfoResponse toHotelFullInfoResponse(Hotel hotel);

    @Named("addressToAddressResponse")
    AddressResponse addressToAddressResponse(Address address);

    @Named("contactToContactResponse")
    ContactResponse contactToContactResponse(Contact contact);

    @Named("arrivalTimeToArrivalTimeResponse")
    ArrivalTimeResponse arrivalTimeToArrivalTimeResponse(ArrivalTime arrivalTime);

    List<HotelResponse> toHotelResponseList(List<Hotel> hotels);

    default List<String> mapAmenities(Set<Amenity> amenities) {
        return amenities.stream()
                .map(Amenity::getName)
                .collect(Collectors.toList());
    }
    @Named("addressToString")
    default String addressToString(Address address) {
        return address == null ? null : address.toString();
    }

    @Mapping(source = "address", target = "address", qualifiedByName = "addressToString")
    @Mapping(source = "contacts.phone", target = "phone")
    HotelResponse toHotelResponse(Hotel hotel);


    @Mapping(source = "address", target = "address")
    @Mapping(source = "contacts", target = "contacts")
    @Mapping(source = "arrivalTime", target = "arrivalTime")
    @Mapping(target = "amenities", ignore = true)
    Hotel toHotel(HotelRequest hotelRequest);

    Address toAddress(AddressRequest addressRequest);

    Contact toContact(ContactRequest contactRequest);

    ArrivalTime toArrivalTime(ArrivalTimeRequest arrivalTimeRequest);

}