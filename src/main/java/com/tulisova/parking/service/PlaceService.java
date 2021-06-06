package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;

import java.util.*;

public interface PlaceService {
    Collection<Place> findAll();
    Collection<Place> findFreePlaces(String startDateTime, Long locationId);
    void createPlaces(Collection<Place> places);
    String findPlacesNameByLocation(Location dbLocation);
    void softDeleteByLocationId(Long locationId);
    Collection<Place> findAllByLocationId(Long locationId);

    Place findById(Long placeId);

    Long deleteById(Long placeId);
}
