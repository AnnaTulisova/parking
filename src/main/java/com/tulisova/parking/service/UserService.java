package com.tulisova.parking.service;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.dao.model.dto.*;
import org.springframework.security.core.userdetails.*;

public interface UserService extends UserDetailsService {
    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException;
    boolean emailExist(String email);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
