package com.example.superApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.superApp.payUrFren.entity")
public class SuperAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SuperAppApplication.class, args);
	}

}
