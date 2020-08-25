package com.petrovpavel.remoteservice.config;

import com.kuliginstepan.dadata.client.DadataClient;
import com.kuliginstepan.dadata.client.DadataClientAutoConfiguration;
import com.kuliginstepan.dadata.client.DadataClientBuilder;
import com.kuliginstepan.dadata.client.DadataClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


/**
 * DadataClientConfig.
 *
 * @author Pavel_Petrov
 */
//@Configuration
public class DadataClientConfig {//extends DadataClientAutoConfiguration {
//
//    @Bean
//    @Override
//    public DadataClient dadataClient(DadataClientProperties clientProperties) {
//        clientProperties.setBaseUrl("https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/address");
//        clientProperties.setToken("d4e618debfa69d55261ad8fb4a8220666ba520bb");
//        clientProperties.setTimeout(Duration.ofSeconds(10));
//        return (new DadataClientBuilder()).token(clientProperties.getToken()).timeout(clientProperties.getTimeout()).baseUrl(clientProperties.getBaseUrl()).build();
//    }
}
