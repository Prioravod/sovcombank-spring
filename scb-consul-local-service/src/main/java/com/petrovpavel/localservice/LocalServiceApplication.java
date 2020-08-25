package com.petrovpavel.localservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class LocalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalServiceApplication.class, args);
    }

}
