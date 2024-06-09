package com.korotkov.hotelsapi.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Basic information about the error")
public class ApiError {
    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error message", example = "Bad request")
    private String message;

    @Schema(description = "List of error details", example = "[\"Field 'name' is required\"]")
    private List<String> errors;
}