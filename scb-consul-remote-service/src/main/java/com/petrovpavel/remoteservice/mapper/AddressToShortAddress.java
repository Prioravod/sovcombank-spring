package com.petrovpavel.remoteservice.mapper;

import com.kuliginstepan.dadata.client.domain.address.Address;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * AddressToShortAddress.
 *
 * @author Pavel_Petrov
 */
@Component
@RequiredArgsConstructor
public class AddressToShortAddress extends CustomMapper<Address, ShortAddress> {

    @Override
    public void mapAtoB(Address from, ShortAddress to, MappingContext context) {
        to.setCity(from.getCity());
        to.setStreet(from.getStreet());
        to.setHouse(from.getHouse());
        to.setPostalCode(from.getPostalCode());
        to.setFiasId(from.getFiasId());
        to.setKladrId(from.getKladrId());
    }

}
