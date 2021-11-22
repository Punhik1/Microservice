package com.punhik.microservices.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.punhik.microservices.limitsservice.beans", "com.punhik.microservices.limitsservice.controllers","com.punhik.microservices.limitsservice.configuration"})
public class LimitsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitsServiceApplication.class, args);
	}

}
