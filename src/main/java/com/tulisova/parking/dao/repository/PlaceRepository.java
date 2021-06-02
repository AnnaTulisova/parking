package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select p from Place p where deleted = false and location_id = ?#{#locationId}")
    Collection<Place> findByLocationId(Long locationId);

    @Query("select p from Place p where deleted = false")
    Collection<Place> findAllPlaces();

    @Query("SELECT place.id FROM Reservation WHERE location.id = ?#{#locationId} AND startDateTime <= ?#{#startDateTime} AND endDateTime >= ?#{#startDateTime} ")
    Collection<Long> findAllReservedPlaceIds(@Param("locationId") Long locationId, @Param("startDateTime") LocalDateTime startDateTime);

    @Modifying
    @Query("update Place set deleted = true where location_id = ?#{#locationId}")
    void softDeleteByLocationId(long locationId);

    @Modifying
    @Transactional
    @Query("update Place set deleted = true where id = ?#{#placeId}")
    void softDeleteByPlaceId(Long placeId);
}
