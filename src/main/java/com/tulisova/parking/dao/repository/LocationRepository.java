package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByAddress(String address);

    //Collection<Location> findAllWhereLocationIdNotIn(Collection<Long> ids);
}
