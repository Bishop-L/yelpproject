/**
 * Service for detecting emotions in images using a remote API.
 * 
 * @author Bishop
 * @version 1.0
 */

package com.github.bishopl.reviewsFromApi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.bishopl.reviewsFromApi.model.RapidApiData;
import com.github.bishopl.reviewsFromApi.model.YelpReview;
import com.github.bishopl.reviewsFromApi.model.YelpUser;


@Service
public class EmotionDetectService {
    private final String rapidAPIURL = "https://emotion-detection2.p.rapidapi.com/emotion-detection";
    private final String rapidAPIHost = "emotion-detection2.p.rapidapi.com";
    private final String rapidAPIKey;
    private final RestTemplate restTemplate;

    @Autowired
    public EmotionDetectService(RestTemplate restTemplate, @Value("${rapid.api.key:defaultApiKey}")String rapidAPIKey) {
        this.rapidAPIKey = rapidAPIKey;
        this.restTemplate = restTemplate;
    }

    
    /**
     * Fetches emotion data for a given image URL.
     *
     * @param url The URL of the image to analyze for emotions.
     * @return A string representing the detected emotion or an error message.
     */
    private String fetchEmotion(String url) {
        
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

            ResponseEntity<List<RapidApiData>> response = restTemplate.exchange(
                    rapidAPIURL,
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<List<RapidApiData>>() {}
            ); 

            List<RapidApiData> imageData = response.getBody();

            //Ensure we have results, and only get the first one
            if(!imageData.isEmpty()){
                emotionData = imageData.get(0).emotion().value();  
            }               

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emotionData;
    }

    /**
     * Adds emotion data to each user in a list of YelpReview objects.
     *
     * @param reviews A list of YelpReview objects.
     */
    public void addEmotionData(List<YelpReview> reviews) {
            for (YelpReview review : reviews) {
                YelpUser user = review.user();
                String imageUrl = user.getImage_url();
                String emotionData = fetchEmotion(imageUrl);
                user.setEmotionData(emotionData);
            }

    }

}
    

