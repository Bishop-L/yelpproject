/**
 * Model class for the Yelp User record,
 * Added Emotion Data to Yelp Record
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.yelpproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpUser {
    private String name;
    private String profile_url;
    private String image_url;
    private String emotionData; // Added field for the Emotion Label

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
 
    /** 
     * @param name
     */
    protected void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @return String
     */
    public String getProfile_url() {
        return profile_url;
    }

    
    /** 
     * @param profile_url
     */
    protected void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    
    /** 
     * @return String
     */
    public String getImage_url() {
        return image_url;
    }

    
    /** 
     * @param image_url
     */
    protected void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    
    /** 
     * @return String
     */
    public String getEmotionData() {
        return emotionData;
    }

    
    /** 
     * @param emotionData
     */
    public void setEmotionData(String emotionData) {
        this.emotionData = emotionData;
    }

}
