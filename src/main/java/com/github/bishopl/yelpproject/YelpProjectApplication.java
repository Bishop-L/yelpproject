/**
 * The YelpProjectApplication class contains the runnable for the application and a test endpoint.
 * process the API responses.
 *
 * @author Bishop
 * @version 1.0
 */
package com.github.bishopl.yelpproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YelpProjectApplication {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(YelpProjectApplication.class, args);
	}

	/**
	 * An endpoint for simple and friendly testing of the server. 
	 *
	 * @param name The name to include in the greeting (optional).
	 * @return A greeting message in the format "Hello {name}!" or "Hello World!" if no name is provided.
	 */
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

}
