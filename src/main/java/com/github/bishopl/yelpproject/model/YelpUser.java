/**
 * Model class for the Yelp User record
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.yelpproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YelpUser(String name, String profile_url, String image_url) { }
