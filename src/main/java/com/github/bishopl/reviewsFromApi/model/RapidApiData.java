/**
 * Model class for the Rapid API Reponse record, trimming excess fields
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.reviewsFromApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RapidApiData(Emotion emotion) {}
