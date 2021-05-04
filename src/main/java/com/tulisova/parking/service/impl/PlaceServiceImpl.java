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
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    public Collection<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Collection<Place> findByLocationId(Long locationId)
    {
        return placeRepository.findByLocationId(locationId);
    }

    @Override
    public Collection<Place> findAllByLocationIdAndNotPlaceId(Long locationId, Collection<Long> placeIds)
    {
        return placeRepository.findByLocationId(locationId)
                .stream()
                .filter(it -> !placeIds.contains(it.getId()))
                .collect(Collectors.toList());
    }
}
