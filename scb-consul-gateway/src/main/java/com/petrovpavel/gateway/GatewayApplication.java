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
                .route(r -> r.path("/api/local/**")
                        .filters(f -> f.rewritePath("/api/local/(?<remains>.*)", "/${remains}")
//                                .hystrix(h -> h.setFallbackUri("forward:/to-remote-service"))
                        )
                        .uri("lb://local-service")
                        .id("local-service"))
                .route( r-> r.path("/api/remote/**")
                        .filters(f -> f.rewritePath("/api/remote/(?<remains>.*)","/${remains}")
//                                .hystrix(h -> h.setFallbackUri("forward:/to-local-service"))
                        )
                        .uri("lb://remote-service")
                        .id("remote-service")
                )
                .build();
    }
}
//args:
//            name: local-service-fallback
//            fallbackUri: forward:/fallback/foo

//return builder.routes()
//        .route(p -> p
//            .path("/get")
//            .filters(f -> f.addRequestHeader("Hello", "World"))
//            .uri("http://httpbin.org:80"))
//        .route(p -> p
//            .host("*.hystrix.com")
//            .filters(f -> f.hystrix(config -> config
//                .setName("mycmd")
//                .setFallbackUri("forward:/fallback")))
//            .uri("http://httpbin.org:80"))
//        .build();

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/api/local/**")
//                        .filters(f -> f.rewritePath("/api/local/(?<remains>.*)", "/${remains}"))
//                        .uri("lb://local-service")
//                        .id("local-service"))
//                .route( r-> r.path("/api/remote/**")
//                        .filters(f -> f.rewritePath("/api/remote/(?<remains>.*)","/${remains}"))
//                        .uri("lb://remote-service")
//                        .id("remote-service")
//                )
//                .build();
//    }
//@Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/api/local/**")
//                        .filters(f -> f.rewritePath("/api/local/(?<remains>.*)", "/${remains}")
//                                .hystrix(h -> h.setFallbackUri("forward:/to-remote-service"))
//                        )
//                        .uri("lb://local-service")
//                        .id("local-service"))
//                .route( r-> r.path("/api/remote/**")
//                        .filters(f -> f.rewritePath("/api/remote/(?<remains>.*)","/${remains}")
//                                .hystrix(h -> h.setFallbackUri("forward:/to-local-service"))
//                        )
//                        .uri("lb://remote-service")
//                        .id("remote-service")
//                )
//                .build();
//    }
