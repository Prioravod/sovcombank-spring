package com.petrovpavel.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/find/**")
                        .filters(f -> f.rewritePath("/api/find/(?<remains>.*)", "/${remains}")
                                .hystrix(h -> h.setFallbackUri("forward:/to-remote-service"))
                        )
                        .uri("lb://local-service")
                        .id("local-service"))
                .route( r-> r.path("/api/remote-find/**")
                        .filters(f -> f.rewritePath("/api/remote-find/(?<remains>.*)","/${remains}")
                                .hystrix(h -> h.setFallbackUri("forward:/to-exit"))
                        )
                        .uri("lb://remote-service")
                        .id("remote-service")
                )
                .build();
    }
}
