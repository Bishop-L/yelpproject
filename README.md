# yelpproject
Skills Assessment using Yelp.
Takes a business id from Yelp and displays the latest three reviews. Runs the profile image through an Emotion analyzer to determine if any emotion data is present and includes that in the response.
If no business id is provided, it defaults to a pre-chosen diner. 

Requires Java 17+.
Should work on 14+, but untested.

Requires a developer API Key from Yelp
https://www.yelp.com/developers

And an APIKey from RapidAPI
https://rapidapi.com/

These will need to be added in the corresponding fields in application.properties.
