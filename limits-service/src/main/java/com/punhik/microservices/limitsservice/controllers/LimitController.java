package com.punhik.microservices.limitsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punhik.microservices.limitsservice.beans.Limit;
import com.punhik.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration configuration;
	

	@GetMapping("/limits")
	public Limit retrieveLimits() {
		return new Limit(configuration.getMinimum(),configuration.getMaximum());
		//return new Limit(1,100);
		
	}
	
}
