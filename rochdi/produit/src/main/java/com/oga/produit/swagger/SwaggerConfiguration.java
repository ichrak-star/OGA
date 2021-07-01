package com.oga.produit.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SwaggerConfiguration {

    public static final String AUTHORIZATION_HEADER="Authorization";
    @Bean
    public Docket cloneApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(getInfoApi());
    }

    private ApiInfo getInfoApi() {
        return new ApiInfoBuilder()
                .title("REST API Ecommerce")
                .version("8")
                .description("Offre des informations sur les API du projet Ecommerce")
                .contact(new Contact("Ichrak DRIDI", "", "devdridi@gmail.com"))
                .license("Licence : 0.0.1")
                .build();
    }





}


