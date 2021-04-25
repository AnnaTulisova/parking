package com.tulisova.parking.dao.repository;

import com.tulisova.parking.dao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByEmail(String email);
    User findByEmail(String email);
}
