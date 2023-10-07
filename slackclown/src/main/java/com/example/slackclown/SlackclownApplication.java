package com.example.slackclown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.slackclown.entity")
public class SlackclownApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlackclownApplication.class, args);
	}

}
