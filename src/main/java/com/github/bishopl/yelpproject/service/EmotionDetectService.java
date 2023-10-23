package com.github.bishopl.yelpproject.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



@Service
public class EmotionDetectService {
    private final String rapidAPIURL = "https://emotion-detection2.p.rapidapi.com/emotion-detection";
    private final String rapidAPIHost = "emotion-detection2.p.rapidapi.com";
    private final String rapidAPIKey;
    private final RestTemplate restTemplate;

    public EmotionDetectService(RestTemplate restTemplate, @Value("${rapid.api.key}") String rapidAPIKey) {
        this.rapidAPIKey = rapidAPIKey;
        this.restTemplate = restTemplate;
    }

    public String fetchEmotion(String url) {
        
        //Default value in case any issues in API call
        String emotionData = "Could not analyze emotion."; 

        try {
            //Build the response body from the url parameter
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonRequest = objectMapper.createObjectNode();
            jsonRequest.put("url", url);
            String jsonRequestString = jsonRequest.toString();            

            //Build the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-RapidAPI-Key", rapidAPIKey);
            headers.add("X-RapidAPI-Host", rapidAPIHost);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequestString, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    rapidAPIURL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            emotionData = response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to fetch emotion data";
        }

        //Return a value even if API request isn't successful
        return emotionData;
    }
}
    

