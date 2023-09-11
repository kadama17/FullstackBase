package com.kadama.solution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // L'URL que vous souhaitez autoriser
            .allowedOrigins("http://localhost:4200") // L'origine autorisée (votre frontend)
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
            .allowCredentials(true); // Autorise les cookies et les en-têtes d'autorisation
    }
}
