package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Collection<Place> findByLocationId(Long locationId);
}
