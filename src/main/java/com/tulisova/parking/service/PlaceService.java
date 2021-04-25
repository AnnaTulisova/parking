package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;

import java.util.*;

public interface PlaceService {
    Collection<Place> findAll();
    Collection<Place> findByLocationId(Long locationId);
    Collection<Place> findAllByLocationIdAndNotPlaceId(Long locationId, Collection<Long> placeIds);
}
