/**
 * Model class for the Yelp API Response to fully consume it for parsing
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.reviewsFromApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class YelpApiResponse {
    @JsonProperty("reviews")
    private List<YelpReview> reviews;

    /** 
     * Getter method to return the list of reviews
     * @return List<YelpReview>
     */
    public List<YelpReview> getReviews() {
        return reviews;
    }
}