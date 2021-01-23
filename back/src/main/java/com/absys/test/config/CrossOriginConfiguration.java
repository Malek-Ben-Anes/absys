package com.absys.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CrossOriginConfiguration {

    private static String[] CROSS_ORIGINS_ALLOWED;

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

    @Value("#{'${app.crossOrigin.allowed}'.split('; ')}")
    private void setCorsOriginsAllowed(String[] corsOriginsAllowed) {
		CROSS_ORIGINS_ALLOWED = Stream.of(corsOriginsAllowed)
				.map(allowedUrl -> "http://" + allowedUrl)
				.toArray(String[]::new);
    }
}
