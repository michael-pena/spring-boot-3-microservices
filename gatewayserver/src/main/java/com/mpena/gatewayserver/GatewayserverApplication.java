package com.mpena.gatewayserver;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.cloud.client.circuitbreaker.Customizer;

import org.springframework.http.HttpMethod;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator configRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
			.route(p -> p
				.path("/bank/accounts/**")
				.filters( f -> f.rewritePath("/bank/accounts/(?<segment>.*)","/${segment}")
					.retry(retryConfig -> retryConfig.setRetries(3)
						.setMethods(HttpMethod.GET)
						.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
					.circuitBreaker(config -> config.setName("accountsCircuitBreaker")
					.setFallbackUri("forward:/contactSupport")))
				.uri("lb://ACCOUNTS"))
			.route(p -> p
				.path("/bank/customers/**")
				.filters( f -> f.rewritePath("/bank/customers/(?<segment>.*)","/${segment}")
				.retry(retryConfig -> retryConfig.setRetries(3)
						.setMethods(HttpMethod.GET)
						.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true))
					.circuitBreaker(config -> config.setName("customersCircuitBreaker")
					.setFallbackUri("forward:/contactSupport")))
				.uri("lb://CUSTOMERS"))
			.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}


}