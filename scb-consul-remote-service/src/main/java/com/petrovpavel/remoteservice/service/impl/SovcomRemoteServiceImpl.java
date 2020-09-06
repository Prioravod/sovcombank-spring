package com.petrovpavel.remoteservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import com.kuliginstepan.dadata.client.DadataClient;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.Address;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * SovcomServiceImpl.
 *
 * @author Pavel_Petrov
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class SovcomRemoteServiceImpl {

    private final MapperFacade mapper;
    private final DadataClient client;

    private Suggestion<Address> response = null;

    public ShortAddress getShortAddressBySuggestion(String fiasCode)  {
        callDadataOnNewThread(fiasCode);
        ShortAddress res = ((response != null) && (response.getData() != null)) ?
                mapper.map(response.getData(), ShortAddress.class) : new ShortAddress();
        if (res.getFiasId() == null) {
            try {
                Thread.sleep(2000);
            } catch (Exception ignored) {}
        }
        return res;

    }

    void callDadataOnNewThread(String fiasCode) {
        Thread thread1 = null;
        try {
            thread1 = new Thread(() -> {
                try {
                    response = client.findAddressById(fiasCode).block();
                }
                catch (Exception ex) {
                    log.warn("DADATA exception: {}", ex.getLocalizedMessage());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {}
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
