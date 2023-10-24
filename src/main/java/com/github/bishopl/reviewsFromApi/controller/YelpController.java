/**
 * The YelpController class handles HTTP requests related to Yelp reviews for businesses.
 * It provides an endpoint to retrieve Yelp reviews for a specific business based on its unique identifier (ID).
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.reviewsFromApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.bishopl.reviewsFromApi.model.YelpReview;
import com.github.bishopl.reviewsFromApi.service.EmotionDetectService;
import com.github.bishopl.reviewsFromApi.service.YelpService;

@RestController
public class YelpController {
    private final YelpService yelpService;
    private final EmotionDetectService emoService;

    @Autowired
    public YelpController(YelpService yelpService, EmotionDetectService emoService) {
        this.yelpService = yelpService;
        this.emoService = emoService;
    }
   
    /**
     * Retrieves a list of Yelp reviews for a business identified by its unique identifier (ID).
     *
     * This method handles GET requests to the '/reviews' endpoint, allowing clients to fetch Yelp
     * reviews for a specific business. After retrieving, the reviews are fed through a RapidAPI
     * emotion detection filter for faces, and adds that value to the review.It accepts the 'id' 
     * parameter, which should be the unique identifier of the target business. If the 'id' parameter 
     * is not provided, a default value is used.
     *
     * @param id The unique identifier of the business for which to retrieve reviews.
     * @return A list of YelpReview objects representing the reviews for the specified business.
     */
    @GetMapping("/reviews")
    public List<YelpReview> getReviews(@RequestParam(value = "id", defaultValue = "6iJ_E5tMJII601mrzwwdrQ") String id) {
        List<YelpReview> reviews = yelpService.fetchYelpReviews(id).getReviews();
        emoService.addEmotionData(reviews);
        return reviews;
    }
}