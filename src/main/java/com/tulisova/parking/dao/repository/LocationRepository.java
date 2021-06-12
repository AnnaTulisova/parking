package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Collection<Location>findByAddressContaining(String address);

    @Query("select l from Location l where deleted = false")
    Collection<Location> findAllLocations();

    @Modifying
    @Query("update Location set deleted = true where id = ?#{#locationId}")
    void softDeleteById(long locationId);
}
