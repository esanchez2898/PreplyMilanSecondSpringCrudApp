package com.qualitystream.tutorial.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.qualitystream.tutorial")
public class SpringBootConfig implements WebMvcConfigurer {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}

