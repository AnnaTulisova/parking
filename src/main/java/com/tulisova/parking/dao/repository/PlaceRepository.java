package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Collection<Place> findByLocationId(Long locationId);
    @Query("SELECT place.id FROM Reservation WHERE location.id = ?#{#locationId} AND startDateTime <= ?#{#startDateTime} AND endDateTime >= ?#{#startDateTime} ")
    Collection<Long> findAllReservedPlaceIds(@Param("locationId") Long locationId, @Param("startDateTime") LocalDateTime startDateTime);
}
