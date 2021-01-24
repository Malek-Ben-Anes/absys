package com.absys.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOriginConfiguration {

    private static String[] CROSS_ORIGINS_ALLOWED;

    @Value("#{'${app.crossOrigin.allowed}'.split('; ')}")
    private void setCorsOriginsAllowed(String[] corsOriginsAllowed) {
        CROSS_ORIGINS_ALLOWED = corsOriginsAllowed;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(CROSS_ORIGINS_ALLOWED)
                        .allowedMethods("*")
                        .maxAge(3600);
            }
        };

    }
}
