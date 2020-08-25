package com.petrovpavel.localservice.repository;

import com.petrovpavel.localservice.domain.Location;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * LocationRepository.
 *
 * @author Pavel_Petrov
 */
@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long>,
        JpaSpecificationExecutor<Location> {

    List<Location> findAllByCity(String city);

    Location findByFiasCode(String fiasCode);
}
