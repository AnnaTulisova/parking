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
    private final ReservationService reservationService;

    @Override
    public Collection<Place> findAll() {
        return placeRepository.findAllPlaces();
    }

    @Override
    public Collection<Place> findFreePlaces(String startDateTime, Long locationId) {
        LocalDateTime startDateTimeTotal = LocalDateTime.parse(startDateTime);
        Collection<Long> reservedPlaceIds = placeRepository.findAllReservedPlaceIds(locationId, startDateTimeTotal);
        return findAllByLocationId(locationId)
                .stream()
                .filter(it-> !reservedPlaceIds.contains(it.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Place> findAllByLocationId(Long locationId) {
        return placeRepository.findByLocationId(locationId);
    }

    @Override
    public void createPlaces(Collection<Place> places) {
        placeRepository.saveAll(places);
    }

    @Override
    public String findPlacesNameByLocation(Location dbLocation) {
        Collection<Place> places = dbLocation.getPlaces();
        return places.stream().map(Place::getName).collect(Collectors.joining(","));
    }

    @Override
    public void softDeleteByLocationId(Long locationId) {
        placeRepository.softDeleteByLocationId(locationId);
    }

    @Override
    public Place findById(Long placeId) {
        return placeRepository.getById(placeId);
    }

    @Override
    public Long deleteById(Long placeId) {
        Collection<Reservation> reservationsWithPlace = reservationService.findAllByPlaceId(placeId);
        Location location = placeRepository.getById(placeId).getLocation();
        if(reservationsWithPlace.size() < 1) {
            placeRepository.deleteById(placeId);
        } else {
            softDeleteByPlaceId(placeId);
        }
        return location.getId();
    }

    private void softDeleteByPlaceId(Long placeId) {
        placeRepository.softDeleteByPlaceId(placeId);
    }
//
//    public Collection<Reservation> findAllReservationsByStartDateTime(LocalDateTime startDateTime) {
//        return reservationRepository.findAllByStartDateTime(startDateTime);
//    }


}
