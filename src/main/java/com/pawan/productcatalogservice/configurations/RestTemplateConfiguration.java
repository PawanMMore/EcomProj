package com.pawan.productcatalogservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    // creating restTemplate and registering it as bean for RestApi Configuration
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }

}
