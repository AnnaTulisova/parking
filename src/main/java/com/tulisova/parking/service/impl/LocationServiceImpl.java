package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.extra.*;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final PlaceService placeService;
    private final ReservationService reservationService;

    @Override
    public Collection<Location> findAll() {
       return locationRepository.findAllLocations();
    }

    @Override
    public Location createLocation(LocationDto locationDto) throws IOException {
        Location toDBLocation = new Location()
                .setAddress(locationDto.getAddress())
                .setTenMinuteCoast(locationDto.getTenMinuteCoast())
                .setDeleted(false)
                .setPicture(locationDto.getPicture().getBytes());


        Location fromDBLocation = locationRepository.save(toDBLocation);


        Collection<String> placeList = Arrays.asList(locationDto.getPlaces().split(";"));
        Collection<Place> placesToAdd = placeList.stream().map(placeName -> new Place().setLocation(fromDBLocation).setName(placeName).setDeleted(false)).collect(Collectors.toList());
        placeService.createPlaces(placesToAdd);

        return toDBLocation;
    }

    @Override
    public Collection<LocationDto> findAllLocations() {
        return locationRepository.findAllLocations().stream()
                .map(dbLocation -> new LocationDto(dbLocation, placeService.findPlacesNameByLocation(dbLocation)))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<LocationDto> findAllByAddress(String address) {
        Collection<Location> locations;
        if(address.isEmpty()) {
            locations =  locationRepository.findAllLocations();
        } else {
            locations = locationRepository.findByAddressContaining(address);
        }
        return locations.stream()
                .map(dbLocation -> new LocationDto(dbLocation, placeService.findPlacesNameByLocation(dbLocation)))
                .collect(Collectors.toList());
    }

    @Override
    public Location findById(Long locationId) {
        return locationRepository.getById(locationId);
    }

    @Transactional
    @Override
    public void deleteByLocationId(Long locationId) {
        Collection<Reservation> matchedReservations = reservationService.findAllByLocationId(locationId);
        if(matchedReservations.size() < 1) { locationRepository.deleteById(locationId); }
        else {
            locationRepository.softDeleteById(locationId);
            placeService.softDeleteByLocationId(locationId);
        }
    }

    public void editLocation(LocationDto locationDto) throws IOException {
        Location dbLocation = locationRepository.getById(locationDto.getId());
        dbLocation.setAddress(locationDto.getAddress());
        dbLocation.setTenMinuteCoast(locationDto.getTenMinuteCoast());
        if(!locationDto.getPicture().getOriginalFilename().isEmpty()) {
            dbLocation.setPicture(locationDto.getPicture().getBytes());
        }
        locationRepository.save(dbLocation);
    }

//    @Override
//    public Collection<Location> findAllWhereLocationIdNotIn(Collection<Location> reservedLocations)
//    {
//        return locationRepository.findAllWhereLocationIdNotIn(reservedLocations
//                                 .stream()
//                                 .map(it -> it.getId())
//                                 .collect(Collectors.toList()));
//    }
}
