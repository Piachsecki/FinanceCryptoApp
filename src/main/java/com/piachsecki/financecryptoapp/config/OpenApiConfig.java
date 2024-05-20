package com.piachsecki.financecryptoapp.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FinanceCrypto App")
                        .version("1.0")
                        .description("""
                                This is an finance api which provides
                                getting information needed for every beginning investor
                                about the assets he is interested in.
                                """
                        )
                        .contact(contact()));
    }
    private Contact contact() {
        return new Contact()
                .name("Kacper Piasecki")
                .email("k.r.piasecki2@gmail.com");
    }
}
