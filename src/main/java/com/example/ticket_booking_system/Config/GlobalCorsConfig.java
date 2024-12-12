package com.example.ticket_booking_system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // Create a CorsConfiguration object
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Replace with your frontend URL
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow these headers
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow these methods
        corsConfig.setAllowCredentials(true); // Allow cookies or Authorization header

        // Create a UrlBasedCorsConfigurationSource and register the configuration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Apply CORS to all endpoints

        // Return a new CorsFilter with the configuration source
        return new CorsFilter(source);
    }
}
