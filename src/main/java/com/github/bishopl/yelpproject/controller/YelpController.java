/**
 * The YelpController class handles HTTP requests related to Yelp reviews for businesses.
 * It provides an endpoint to retrieve Yelp reviews for a specific business based on its unique identifier (ID).
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.yelpproject.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.bishopl.yelpproject.service.YelpService;
import com.github.bishopl.yelpproject.model.YelpReview;

@RestController
public class YelpController {
    private final YelpService yelpService;

    public YelpController(YelpService yelpService) {
        this.yelpService = yelpService;
    }
   
    /**
     * Retrieves a list of Yelp reviews for a business identified by its unique identifier (ID).
     *
     * This method handles GET requests to the '/reviews' endpoint, allowing clients to fetch Yelp
     * reviews for a specific business. It accepts the 'id' parameter, which should be the unique
     * identifier of the target business. If the 'id' parameter is not provided, a default value is used.
     *
     * @param id The unique identifier of the business for which to retrieve reviews.
     * @return A list of YelpReview objects representing the reviews for the specified business.
     */
    @GetMapping("/reviews")
    public List<YelpReview> getReviews(@RequestParam(value = "id", defaultValue = "6iJ_E5tMJII601mrzwwdrQ") String id) {
        return yelpService.fetchYelpReviews(id).getReviews();
    }
}