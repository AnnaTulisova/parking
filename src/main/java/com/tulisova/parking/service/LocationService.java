package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;

import java.util.*;

public interface LocationService {
    Collection<Location> findAll();
    Collection<Location> findAllWhereLocationIdNotIn(Collection<Location> reservedLocations);
}
