package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.dto.*;

import java.util.*;

public interface PlaceService {
    Collection<Place> findAll();
    Collection<Place> findFreePlaces(String startDateTime, Long locationId, Boolean forElectroCar);
    void createPlaces(Collection<Place> places);
    String findPlacesNameByLocation(Location dbLocation);
    String findElectroPlacesNameByLocation(Location location);
    void softDeleteByLocationId(Long locationId);
    Collection<Place> findAllByLocationId(Long locationId);
    Place editPlace(Place place);
    Place addPlace(PlaceDto place, Location location);
    Place findById(Long placeId);
    Long deleteById(Long placeId);
}
