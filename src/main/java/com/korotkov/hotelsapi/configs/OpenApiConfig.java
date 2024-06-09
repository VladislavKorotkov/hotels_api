package com.korotkov.hotelsapi.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Hotels API", version = "1.0.0",
                contact = @Contact(
                        name = "Korotkov Vladislav",
                        email = "vladislav.korotkow@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
