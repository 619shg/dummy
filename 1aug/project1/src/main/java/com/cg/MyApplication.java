package com.cg;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {
	private static final Logger LOGGER = Logger.getLogger("MyApplication.class");

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
		LOGGER.info("My Itinerary Application started.");
	}
}
