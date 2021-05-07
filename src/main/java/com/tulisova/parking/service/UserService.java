package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.service.dto.*;

public interface UserService {
    User findUserByEmail(String email);
    User registerNewUser(UserDto userDto);
    User getCurrentUser();
}
