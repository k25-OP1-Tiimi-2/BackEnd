package com.backend.projekti.tiimityo.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// tässä luokassa annetaan oikeudet reactille tehdä pyyntöjä palvelimelta
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // Salli Reactin pyynnöt
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
