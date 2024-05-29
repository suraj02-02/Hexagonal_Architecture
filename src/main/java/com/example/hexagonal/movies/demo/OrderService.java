package com.example.hexagonal.movies.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * This is the main class of spring boot app
 * For now we have excluded any usage of Datasource in our application since
 * it is just a demo application , and it's main use case is to learn the hexagonal architecture.
 *
 * The detail on the hexagonal architecture and how it is implemented in this project can be found at : src/main/resources/static/Hexagonal_Architecture_Explained
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}
}
