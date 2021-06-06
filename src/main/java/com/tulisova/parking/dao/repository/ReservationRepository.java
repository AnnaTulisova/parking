package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where startDateTime between ?#{#startDateTime} and ?#{#endDateTime}")
    Collection<Reservation> findAllByStartDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Collection<Reservation> findAllByUserId(Long userId);

    @Query("select r from Reservation r where location_id = ?#{#locationId}")
    Collection<Reservation> findAllByLocationId(long locationId);

    @Query("select r from Reservation r where place_id = ?#{#placeId}")
    Collection<Reservation> findAllByPlaceId(Long placeId);
}
