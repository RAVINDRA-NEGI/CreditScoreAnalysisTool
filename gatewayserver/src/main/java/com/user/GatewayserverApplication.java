package com.user;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import reactor.core.publisher.Mono;

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
						    		.circuitBreaker(config -> config
						    				.setName("creditCircuitBreaker")
						    				.setFallbackUri("forward:/fallback/credit"))
						    		.retry(retryConfig -> retryConfig
						    				.setRetries(3)
						    				.setStatuses(HttpStatus.INTERNAL_SERVER_ERROR))
						    		.requestRateLimiter(redisRateLimiter -> redisRateLimiter
						    				.setRateLimiter(redisRateLimiter())
						    				.setKeyResolver(userkeyResolver()))
						    		.addRequestHeader("X-GATEWAY-KEY", "super-secret-key")
						    		.rewritePath("/api/credit/(?<segment>.*)" ,"/${segment}")
						    		.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						    		
							.uri("lb://CREDIT-RECORD-SERVICE"))
				.route(r -> r
							.path("/api/user/**")
							.filters(f ->f
									.circuitBreaker(config -> config
						    				.setName("creditCircuitBreaker")
						    				.setFallbackUri("forward:/fallback/user"))
									.retry(retryConfig -> retryConfig
						    				.setRetries(3)
						    				.setStatuses(HttpStatus.INTERNAL_SERVER_ERROR))
									.requestRateLimiter(redisRateLimiter -> redisRateLimiter
						    				.setRateLimiter(redisRateLimiter())
						    				.setKeyResolver(userkeyResolver()))
									.addRequestHeader("X-GATEWAY-KEY", "super-secret-key")
									.rewritePath("/api/user/(?<segment>.*)" ,"/${segment}")
									.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
							.uri("lb://USER-MANAGEMENT-SERVICE"))
				.route(r-> r
							.path("/api/score/**")
							.filters(f -> f
									.circuitBreaker(config -> config
						    				.setName("creditCircuitBreaker")
						    				.setFallbackUri("forward:/fallback/score"))
									.retry(retryConfig -> retryConfig
						    				.setRetries(3)
						    				.setStatuses(HttpStatus.INTERNAL_SERVER_ERROR))
									.requestRateLimiter(redisRateLimiter -> redisRateLimiter
						    				.setRateLimiter(redisRateLimiter())
						    				.setKeyResolver(userkeyResolver()))
									.addRequestHeader("X-GATEWAY-KEY", "super-secret-key")
									.rewritePath("/api/score/(?<segment>.*)" ,"/${segment}")
									.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
							.uri("lb://CREDIT-SCORE-SERVICE"))
							.build();
	}
	
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 2); 
	}
	
	@Bean
	KeyResolver userkeyResolver() {
		return exchange -> 
			Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
					.defaultIfEmpty("anonymous");
		
	}
}


