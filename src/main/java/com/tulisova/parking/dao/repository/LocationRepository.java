package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByAddress(String address);
}
