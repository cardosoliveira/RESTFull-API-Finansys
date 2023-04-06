package br.com.finansys.finansys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // required  setting for angular app to be able to call endpoints from backend using different domain
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}