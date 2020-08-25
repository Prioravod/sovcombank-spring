package com.petrovpavel.remoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class RemoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteServiceApplication.class, args);
    }

}
