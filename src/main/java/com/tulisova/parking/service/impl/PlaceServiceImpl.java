package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    public Collection<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Collection<Place> findFreePlaces(String startDateTime, Long locationId) {
        LocalDateTime startDateTimeTotal = LocalDateTime.parse(startDateTime);
        Collection<Long> reservedPlaceIds = placeRepository.findAllReservedPlaceIds(locationId, startDateTimeTotal);
        return findAllByLocationId(locationId).stream().filter(it-> !reservedPlaceIds.contains(it.getId())).collect(Collectors.toList());
    }

    public Collection<Place> findAllByLocationId(Long locationId) {
        return placeRepository.findByLocationId(locationId);
    }

//
//    public Collection<Reservation> findAllReservationsByStartDateTime(LocalDateTime startDateTime) {
//        return reservationRepository.findAllByStartDateTime(startDateTime);
//    }


}
