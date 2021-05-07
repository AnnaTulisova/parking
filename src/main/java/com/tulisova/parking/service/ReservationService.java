package com.tulisova.parking.service;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.dto.*;

import java.time.*;
import java.util.*;

public interface ReservationService {
    Collection<Reservation> findAll();
    //Collection<Reservation> findAllByStartDateTime(LocalDateTime startDateTime);
    Reservation createReservation(ReservationDto reservationDto);
    //Collection<Place> findFreePlaces(ReservationDto reservationDto);
    //Collection<Location> findFreeLocations(ReservationDto reservationDto);
    Reservation findById(Long id);
    void deleteByReservationId(Long id);
    Collection<Reservation> findAllByUserId(Long userId);
}
