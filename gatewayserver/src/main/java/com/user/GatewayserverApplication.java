package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
							.path("/api/credit/**")
						    .filters(f ->f
						    		.addRequestHeader("X-GATEWAY-KEY", "super-secret-key")
						    		.rewritePath("/api/credit/(?<segment>.*)" ,"/${segment}"))
							.uri("lb://CREDIT-RECORD-SERVICE"))
				.route(r -> r
							.path("/api/user/**")
							.filters(f ->f
									.addRequestHeader("X-GATEWAY-KEY", "super-secret-key")
									.rewritePath("/api/user/(?<segment>.*)" ,"/${segment}"))
							.uri("lb://USER-MANAGEMENT-SERVICE"))
				.route(r-> r
							.path("/api/score/**")
							.filters(f -> f
									.addRequestHeader("X-GATEWAY-KEY", "super-secret-key")
									.rewritePath("/api/score/(?<segment>.*)" ,"/${segment}"))
							.uri("lb://CREDIT-SCORE-SERVICE"))
							.build();
	}

}


