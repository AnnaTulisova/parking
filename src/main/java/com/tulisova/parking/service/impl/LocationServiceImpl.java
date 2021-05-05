package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Collection<Location> findAll() {
       return locationRepository.findAll();
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
