package com.korotkov.hotelsapi.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContactResponse {
    @Schema(description = "The phone number", example = "+375 17 309-80-00")
    private String phone;

    @Schema(description = "The email address", example = "doubletreeminsk.info@hilton.com")
    private String email;
}
