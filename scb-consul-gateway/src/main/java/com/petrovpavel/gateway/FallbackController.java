package com.petrovpavel.gateway;

import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
    public Mono<ShortAddress> localFallback(ServerWebExchange exchange) {
        return redirect(exchange, "local","remote");
    }

    @GetMapping("/to-local-service")
    public Mono<ShortAddress> remoteFallback(ServerWebExchange exchange) {
        return redirect(exchange, "remote","local");
    }

    private Mono<ShortAddress> redirect(ServerWebExchange exchange, String originalService, String destService){
        Throwable throwable = exchange.getAttributeOrDefault(
                ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR,null);
        log.warn("Worked Fallback func in {} service. Try to call {} service. Cause: {}",
                originalService, destService, throwable.getClass());
        LinkedHashSet<URI> originalRequestUris = exchange.getAttributeOrDefault(
                ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR,null);
        String path = originalRequestUris.iterator().next().getPath();
        String newPath = path.replace(originalService, destService);
        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.get()
                .uri(newPath)
                .retrieve()
                .bodyToMono(ShortAddress.class);
    }

}
