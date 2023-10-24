/**
 * The YelpService class is responsible for interacting with the Yelp Fusion API to retrieve
 * business reviews and related data. It provides methods to fetch Yelp reviews and
 * process the API responses.
 *
 * @author Bishop
 * @version 1.0
 * @date 10/19/2023
 */

package com.github.bishopl.reviewsFromApi.service;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.github.bishopl.reviewsFromApi.model.YelpApiResponse;

@Service
public class YelpService {
    private final RestTemplate restTemplate;
    private final String apiKey;

    @Autowired
    public YelpService(RestTemplate restTemplate, @Value("${yelp.api.key:defaultApiKey}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }
    
    /**
     * Fetches Yelp reviews for a business using its unique identifier (ID).
     *
     * This method sends an HTTP GET request to the Yelp Fusion API with the provided business ID
     * and retrieves the reviews associated with the business. It includes the necessary
     * authentication headers for the API request.
     *
     * @param id The unique identifier of the business for which to fetch reviews.
     * @return A YelpApiResponse object containing the reviews and related data.
     */
    public YelpApiResponse fetchYelpReviews(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        RequestEntity<Void> requestEntity = RequestEntity
            .get(URI.create("https://api.yelp.com/v3/businesses/" + id + "/reviews"))
            .headers(headers)
            .build();

        ResponseEntity<YelpApiResponse> response = restTemplate.exchange(
            requestEntity,
            YelpApiResponse.class
        );

        return response.getBody();
    }
}
