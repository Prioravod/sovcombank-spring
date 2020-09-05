package com.petrovpavel.localservice.service;

import com.petrovpavel.localservice.domain.Location;
import com.petrovpavel.localservice.repository.LocationRepository;
import com.petrovpavel.service.openapi.model.ShortAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * sovcomLocalServiceImpl.
 *
 * @author Pavel_Petrov
 */
@Getter
@Service
@RequiredArgsConstructor
public class SovcomLocalServiceImpl {

    private final MapperFacade mapperFacade;
    private final LocationRepository locationRepository;

    public List<Location> findByCity(String city){
        return StringUtils.isNotEmpty(city) ? locationRepository.findAllByCity(city) : new ArrayList<>();
    }

    public ShortAddress findByFiasCode(String fiasCode) {
        Location location = locationRepository.findByFiasCode(fiasCode);
        ShortAddress res = (location != null) ?
                mapperFacade.map(location, ShortAddress.class) : new ShortAddress();
        if (res.getFiasId() == null) {
            try {
                Thread.sleep(2000);
            } catch (Exception ignored) {}
        }
        return res;
    }
}
