package com.punhik.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	 @GetMapping("/sample-api")
	//@Retry(name="sample-api",fallbackMethod = "hardCodedResponse")
	
	//@CircuitBreaker(name="sample-api",fallbackMethod = "hardCodedResponse")
	
	//Rate Limiting :- In 10 sec --> only 10000 calls allow to the sample rest api
    @RateLimiter(name="default")
	
    //BulkHead :- Concurrent calls are allowed for particular api
	//@Bulkhead(name="sample-api")
	
	public String SampleApi() {
		logger.info("Request Api Call Recieved");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);		
//		return forEntity.getBody();
		return "Punhik Api Called";
	}
	
	public String hardCodedResponse(Exception e) {
		return "Service is not up Right Now";
		
	}

}
