package com.korotkov.hotelsapi.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {
    @Schema(description = "The phone number", example = "+375 17 309-80-00", required = true)
    @NotBlank(message = "The 'phone' field cannot be empty")
    private String phone;

    @Schema(description = "The email address", example = "doubletreeminsk.info@hilton.com", required = true)
    @NotBlank(message = "The 'email' field cannot be empty")
    private String email;
}
