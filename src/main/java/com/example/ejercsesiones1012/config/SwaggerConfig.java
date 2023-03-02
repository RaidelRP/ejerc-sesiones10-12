package com.example.ejercsesiones1012.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


/*
No me funciona el url /swagger-ui
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return  new ApiInfo("Spring Boot Laptop API REST",
                "Library API REST docs",
                "1.0",
                "http://google.com",
                new Contact("Raidel","http://google.com","raidel@example.com"),
                "MIT",
                "http://google.com",
                Collections.emptyList());
    }
}
