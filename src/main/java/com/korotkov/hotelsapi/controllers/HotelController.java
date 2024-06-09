package com.korotkov.hotelsapi.controllers;

import com.korotkov.hotelsapi.requests.HotelRequest;
import com.korotkov.hotelsapi.responses.ApiError;
import com.korotkov.hotelsapi.responses.HotelFullInfoResponse;
import com.korotkov.hotelsapi.responses.HotelResponse;
import com.korotkov.hotelsapi.services.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
@Tag(name = "Hotels API", description = "Endpoints for managing hotels")
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "Get all hotels",
            description = "Retrieves a list of all hotels")
    @GetMapping("/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponse> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "Get a hotel by ID",
            description = "Retrieves a hotel by its unique identifier",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Hotel not found",   content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))
            })
    @GetMapping("/hotels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HotelFullInfoResponse getHotelById(
            @Parameter(description = "ID of the hotel to retrieve") @PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @Operation(summary = "Create a new hotel",
            description = "Creates a new hotel with the provided details",
            responses = {
            @ApiResponse(responseCode = "400", description = "Validation failed",   content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponse createHotel(
            @Valid @RequestBody HotelRequest hotelRequest) {
        return hotelService.createHotel(hotelRequest);
    }

    @Operation(summary = "Add amenities to a hotel",
            description = "Adds the specified amenities to the hotel with the given ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    example = "[\"Free parking\", \"Free WiFi\", \"Non-smoking rooms\"]"
                            )
                    )),
            responses = {
                    @ApiResponse(responseCode = "404", description = "Hotel not found",   content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))
            })
    @PostMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelFullInfoResponse addAmenitiesToHotel(
            @Parameter(description = "ID of the hotel to add amenities to") @PathVariable Long id,
            @Parameter(description = "List of amenities to add to the hotel") @NotEmpty @RequestBody List<String> amenities) {
        return hotelService.addAmenitiesToHotel(id, amenities);
    }

    @Operation(summary = "Search for hotels",
            description = "Searches for hotels based on the provided filters")
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponse> searchHotels(
            @Parameter(description = "Name of the hotel to search for") @RequestParam(required = false) String name,
            @Parameter(description = "Brand of the hotel to search for") @RequestParam(required = false) String brand,
            @Parameter(description = "City of the hotel to search for") @RequestParam(required = false) String city,
            @Parameter(description = "County of the hotel to search for") @RequestParam(required = false) String county,
            @Parameter(description = "Amenities of the hotel to search for") @RequestParam(required = false) Set<String> amenities) {
        return hotelService.searchHotels(name, brand, city, county, amenities);
    }

    @Operation(summary = "Get a histogram for a parameter",
            description = "Generates a histogram for the specified parameter",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful response",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "object",
                                            example = "{\n" +
                                                    "  \"Minsk\": 1,\n" +
                                                    "  \"Moscow\": 2,\n" +
                                                    "  \"Mogilev\": 0\n" +
                                                    "}"))),
                    @ApiResponse(responseCode = "400", description = "Invalid parameter for histogram",   content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))
            })
    @GetMapping("/histogram/{param}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> getHistogram(
            @Parameter(description = "Parameter to generate the histogram for") @PathVariable String param) {
        return hotelService.getHistogram(param);
    }
}