package com.example.webflux_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.example.webflux_learning.${sec}")
@EnableR2dbcRepositories(basePackages = "com.example.webflux_learning.${sec}")
public class WebfluxLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxLearningApplication.class, args);
	}

}
