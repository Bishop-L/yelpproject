/**
 * The RestTemplateConfig class for customizing and creating instances of RestTemplate.
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.yelpproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {    
    
    /** 
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}