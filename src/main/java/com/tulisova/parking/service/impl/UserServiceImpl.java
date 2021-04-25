package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.dao.model.dto.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException {
        /*if (userRepository.existByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException(userDto.getEmail());
        }*/

        return new User();
    }

    @Override
    public boolean emailExist(String email) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }

    /*@Override
    public boolean emailExist(String email) {
        return userRepository.existByEmail(email);
    }*/

    /*@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByEmail(userName);
    }
     */
}
