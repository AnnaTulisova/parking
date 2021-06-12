package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
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
    public Collection<Place> findFreePlaces(String startDateTime, Long locationId, Boolean forElectroCar) {
        LocalDateTime startDateTimeTotal = LocalDateTime.parse(startDateTime);
        Collection<Long> reservedPlaceIds = placeRepository.findAllReservedPlaceIds(locationId, startDateTimeTotal);
        Collection<Place> foundedPlaces = findAllByLocationId(locationId)
                .stream()
                .filter(it-> !reservedPlaceIds.contains(it.getId()))
                .collect(Collectors.toList());
        if(forElectroCar) {
            return foundedPlaces
                    .stream()
                    .filter(Place::getForElectroCars)
                    .collect(Collectors.toList());
        }
        return foundedPlaces;
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
    public String findElectroPlacesNameByLocation(Location location) {
        Collection<Place> places = location.getPlaces().stream().filter(place -> place.getForElectroCars() == true ).collect(Collectors.toList());
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

    @Override
    public Place editPlace(Place place) {
        Place dbPlace = placeRepository.getById(place.getId());
        dbPlace.setName(place.getName());
        dbPlace.setForElectroCars(place.getForElectroCars());
        placeRepository.save(dbPlace);
        return dbPlace;
    }
    @Override
    public Place addPlace(PlaceDto place, Location location) {
        Place placeToDB = new Place().setName(place.getName())
                .setForElectroCars(place.getForElectroCars())
                .setLocation(location).setDeleted(false);
        Place dbPlace = placeRepository.save(placeToDB);
        return dbPlace;
    }


    private void softDeleteByPlaceId(Long placeId) {
        placeRepository.softDeleteByPlaceId(placeId);
    }
//
//    public Collection<Reservation> findAllReservationsByStartDateTime(LocalDateTime startDateTime) {
//        return reservationRepository.findAllByStartDateTime(startDateTime);
//    }


}
