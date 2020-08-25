package com.petrovpavel.localservice.mapper;

import com.petrovpavel.localservice.domain.Location;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 * LocationToShortAddress.
 *
 * @author Pavel_Petrov
 */
@Component
@RequiredArgsConstructor
public class LocationToShortAddress extends CustomMapper<Location, ShortAddress> {

    @Override
    public void mapAtoB(Location from, ShortAddress to, MappingContext context) {
        to.setCity(from.getCity());
        to.setStreet(from.getStreet());
        to.setHouse(from.getHouse());
        to.setFiasId(from.getFiasCode());
    }

}

