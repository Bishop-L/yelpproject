/**
 * Model class for the Emotion data
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.reviewsFromApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Emotion(String value) {}

