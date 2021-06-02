package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.dto.*;

import java.io.*;
import java.util.*;

public interface LocationService {
    Collection<Location> findAll();

    Location createLocation(LocationDto locationDto) throws IOException;

    Collection<LocationDto> findAllLocations();

    Collection<LocationDto> findAllByAddress(String address);

    Location findById(Long locationId);

    void deleteByLocationId(Long locationId);

    void editLocation(LocationDto locationDto);
}
