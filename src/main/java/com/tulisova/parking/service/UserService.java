package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.service.dto.*;
import org.springframework.security.core.userdetails.*;

public interface UserService {
    User findUserByEmail(String email);
    User registerNewUser(UserDto userDto);
}
