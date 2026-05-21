package com.qualitystream.tutorial.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.qualitystream.tutorial")
public class SpringBootConfig implements WebMvcConfigurer {

    // Registers ModelMapper as a Bean, allowing Spring to manage
    // its lifecycle and inject it wherever needed
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}

