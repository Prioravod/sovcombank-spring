package com.petrovpavel.remoteservice.resource;

import com.petrovpavel.remoteservice.service.impl.SovcomRemoteServiceImpl;
import com.petrovpavel.service.openapi.api.SovcomResourceApi;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


/**
 * SovcomResource.
 *
 * @author Pavel_Petrov
 */
@Validated
@RestController
@RequiredArgsConstructor
public class SovcomRemoteResource implements SovcomResourceApi {

    private final SovcomRemoteServiceImpl service;

    @Override
    public ResponseEntity<ShortAddress> getAddressByCode(String code) {
        ShortAddress address = service.getShortAddressBySuggestion(code);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(address, headers, address != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
