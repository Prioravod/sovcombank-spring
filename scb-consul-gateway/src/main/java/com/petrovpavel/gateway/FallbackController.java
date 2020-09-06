package com.petrovpavel.gateway;

import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashSet;

/**
 * FallbackController.
 *
 * @author Pavel_Petrov
 */
@Slf4j
@RestController
@RequestMapping
public class FallbackController {

    @GetMapping("/to-remote-service")
    public Mono<ShortAddress> localFallback(ServerWebExchange exchange){
        Throwable throwable = exchange.getAttributeOrDefault(
                ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR,null);
        log.warn("Worked Fallback func in local service. Try to call remote service. Cause: {}", throwable.getClass());
        LinkedHashSet<URI> originalRequestUris = exchange.getAttributeOrDefault(
                ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR,null);
        String path = originalRequestUris.iterator().next().getPath();
        String newPath = path.replace("/find", "/remote-find");
        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.get()
                .uri(newPath)
                .retrieve()
                .bodyToMono(ShortAddress.class);
    }

    @GetMapping("/to-exit")
    public void remoteFallback(ServerWebExchange exchange) {
        String errorDescription = "Something went wrong. Both services are not working or request was incorrect.";
        Throwable throwable = exchange.getAttributeOrDefault(
                ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR,null);
        log.error("{} Cause: {}", errorDescription, throwable.getClass());
    }

}
