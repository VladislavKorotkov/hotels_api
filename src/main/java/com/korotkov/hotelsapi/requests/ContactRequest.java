package com.korotkov.hotelsapi.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {
    @NotBlank(message = "The 'phone' field cannot be empty")
    private String phone;
    @NotBlank(message = "The 'email' field cannot be empty")
    private String email;
}
