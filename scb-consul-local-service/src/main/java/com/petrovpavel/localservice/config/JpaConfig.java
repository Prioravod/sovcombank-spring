package com.petrovpavel.localservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JpaConfig.
 *
 * @author Pavel_Petrov
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.petrovpavel.localservice"})
@EntityScan(basePackages = {"com.petrovpavel.localservice.domain"})
public class JpaConfig {

}


