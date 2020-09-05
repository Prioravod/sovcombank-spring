package com.petrovpavel.localservice.resource;

import com.petrovpavel.localservice.service.SovcomLocalServiceImpl;
import com.petrovpavel.service.openapi.api.SovcomResourceApi;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * SovcomLocalResource.
 *
 * @author Pavel_Petrov
 */
@Validated
@RestController
@RequiredArgsConstructor
public class SovcomLocalResource implements SovcomResourceApi {

    private final SovcomLocalServiceImpl service;
//    private final WebClient webClient = WebClient.create("lb://remote-service");

//    @Override
//    public Mono<ShortAddress> fallback(ServerWebExchange failedExchange) {
//        log.warn("Local fallback is used. ServerWebExchange ex = {}", failedExchange);
//
//        //http://localhost:8080/api/remote/address/b99f274f-75b8-4172-941d-b7c40a2b1ae5
//
//            UriComponents uriComponents = UriComponentsBuilder.newInstance()
//                    .uri(URI.create("lb://remote-service"))
//                    .path("/api/remote/**")
//                    .queryParams(failedExchange.getRequest().getQueryParams())
//                    .build();
//
//            return WebClient.create(uriComponents.toUriString())
//                    .get()
//                    .accept(MediaType.TEXT_XML)
//                    .exchange()
//                    .doOnSuccess(clientResponse -> {
//                        // Copy the headers from the public service's response back to our exchange's response
//                        failedExchange.getResponse().getHeaders()
//                                .addAll(clientResponse.headers().asHttpHeaders());
//                    })
//                    .flatMap(clientResponse -> clientResponse.bodyToMono(ShortAddress.class));
//    }

    @Override
    public ResponseEntity<ShortAddress> getAddressByCode(String code) {
        ShortAddress address = service.findByFiasCode(code);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(address, headers, HttpStatus.OK);
    }
}
