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

    @Override
    public ResponseEntity<ShortAddress> getAddressByCode(String code) {
        ShortAddress address = service.findByFiasCode(code);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(address, headers, HttpStatus.OK);
    }
}
