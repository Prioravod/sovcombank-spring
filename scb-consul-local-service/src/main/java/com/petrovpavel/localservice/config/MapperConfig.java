package com.petrovpavel.localservice.config;

import com.petrovpavel.localservice.domain.Location;
import com.petrovpavel.localservice.mapper.LocationToShortAddress;
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

    private final LocationToShortAddress locationToShortAddress;

    @Override
    public void configure(MapperFactory mapperFactory) {

        mapperFactory
                .classMap(Location.class, ShortAddress.class)
                .customize(locationToShortAddress)
                .register();
    }
}
