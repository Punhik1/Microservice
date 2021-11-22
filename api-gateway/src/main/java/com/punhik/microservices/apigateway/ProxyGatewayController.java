package com.punhik.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyGatewayController {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route(p->p.path("/get")
						.filters(f->f.addRequestHeader("myHeader", "MyUrI")
						.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(a->a.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				.route(a->a.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(a->a.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(a->a.path("/currency-conversion-new/**")
						.filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)", 
                              "/currency-conversion/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}
	

}
