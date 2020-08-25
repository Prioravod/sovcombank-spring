package com.petrovpavel.remoteservice.config;

import com.kuliginstepan.dadata.client.domain.address.Address;
import com.petrovpavel.remoteservice.mapper.AddressToShortAddress;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

/**
 * MapperConfig.
 *
 * @author Pavel_Petrov
 */
@Component
@AllArgsConstructor
public class MapperConfig implements OrikaMapperFactoryConfigurer {

    private final AddressToShortAddress addressToShortAddress;

    @Override
    public void configure(MapperFactory mapperFactory) {

        mapperFactory
                .classMap(Address.class, ShortAddress.class)
                .customize(addressToShortAddress)
                .register();
    }
}
