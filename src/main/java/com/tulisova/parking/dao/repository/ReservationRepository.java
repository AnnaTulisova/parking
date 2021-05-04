package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select p from #{#entityName} where p.start_date_time >= #{startDateTime} and p.end_date_time <= #{startDateTime} ")
    List<Reservation> findAllByStartDateTime(LocalDateTime startDateTime);
}
