/**
 * Model class for the Yelp Review record, trimming excess fields
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.reviewsFromApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YelpReview(YelpUser user, int rating, String time_created, String text, String url) {}
