package com.healthcare.uman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration CORS
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * Enables CORS for all endpoints
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedOriginPatterns("https://*.healthcare.fr", "http://localhost:[*]").allowedMethods("*").allowedHeaders("*");
    }

    /**
     * Redirect "/" to swagger ui
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }
}
