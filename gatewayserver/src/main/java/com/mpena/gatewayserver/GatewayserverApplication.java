package com.mpena.gatewayserver;

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
	public RouteLocator configRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
			.route(p -> p
				.path("/bank/accounts/**")
				.filters( f -> f.rewritePath("/bank/accounts/(?<segment>.*)","/${segment}"))
				.uri("lb://ACCOUNTS"))
			.route(p -> p
				.path("/bank/customers/**")
				.filters( f -> f.rewritePath("/bank/customers/(?<segment>.*)","/${segment}"))
				.uri("lb://CUSTOMERS"))
			.build();
	}
}