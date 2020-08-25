package com.petrovpavel.remoteservice.service.impl;

import com.kuliginstepan.dadata.client.domain.address.AddressRequestBuilder;
import com.kuliginstepan.dadata.client.exception.DadataException;
import ma.glasnost.orika.MapperFacade;
import com.kuliginstepan.dadata.client.DadataClient;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.Address;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.table.TableRowSorter;

/**
 * SovcomServiceImpl.
 *
 * @author Pavel_Petrov
 */
@Getter
@Service
@RequiredArgsConstructor
public class SovcomRemoteServiceImpl {

    private final MapperFacade mapper;

    @Autowired
    DadataClient client;

    private Suggestion<Address> response = null;

    public ShortAddress getShortAddressBySuggestion(String fiasCode)  {
        run1(fiasCode);
        return response.getData() != null ? mapper.map(response.getData(), ShortAddress.class) : null;
    }

    void run1(String fiasCode) {
        Thread thread1 = null;
        try {
            thread1 = new Thread(() -> {
                try {
                    response = client.findAddressById(fiasCode).block();
                }
                catch (DadataException ex) {
                    System.out.println(ex.getDetails());
                }
            });
            thread1.start();
            thread1.join();
        }
        catch (Exception ex){
            assert thread1 != null;
            thread1.stop();
        }
    }

}

