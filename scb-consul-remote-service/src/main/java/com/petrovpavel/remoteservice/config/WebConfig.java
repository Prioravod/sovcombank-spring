package com.petrovpavel.remoteservice.config;

import com.kuliginstepan.dadata.client.DadataClient;
import com.kuliginstepan.dadata.client.DadataClientBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.Optional;

/**
 * WebConfig.
 *
 * @author Pavel_Petrov
 */
@Configuration
public class WebConfig {

    @Bean
    public WebClient createWebClient() throws SSLException {
        String baseUrl = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";
        String token = "Token d4e618debfa69d55261ad8fb4a8220666ba520bb";
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", token)
                .build();
    }

    @Bean
    public DadataClient dadataClient(WebClient webClient) {
        return (new DadataClientBuilder())
                .webClient(webClient)
                .build();
    }
}
