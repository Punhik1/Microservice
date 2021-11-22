package com.punhik.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConvertorController {
	
	@Autowired
	private CurrencyExchangeProxy currencyexchangeproxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConverter(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		     
		   // Date d1 = new Date();
		    HashMap<String,String> uriVariables = new HashMap<>();
		    uriVariables.put("newfromKey", from);
		    uriVariables.put("newtoKey", to);
		    ResponseEntity<CurrencyConversion> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{newfromKey}/to/{newtoKey}", CurrencyConversion.class,uriVariables);
		    CurrencyConversion currencyconversion = responseEntity.getBody();
		    return new  CurrencyConversion(currencyconversion.getId() ,from ,to,quantity,currencyconversion.getConversionMultiple(),quantity.multiply(currencyconversion.getConversionMultiple()),currencyconversion.getEnvironment()+""+"Rest Template");	
	}
	
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConverterFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		     
		   CurrencyConversion currencyconversion = currencyexchangeproxy.getRetriveCurrencyExchange(from, to);
		    return new  CurrencyConversion(currencyconversion.getId() ,from ,to,quantity,currencyconversion.getConversionMultiple(),quantity.multiply(currencyconversion.getConversionMultiple()),currencyconversion.getEnvironment()+" "+"Feign cLient");	
	}

}
