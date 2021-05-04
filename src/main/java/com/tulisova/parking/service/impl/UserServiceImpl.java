package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.repository.*;
import com.tulisova.parking.service.*;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.exception.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with email %s not founded", email)));
    }

    @Override
    public User registerNewUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistException(userDto.getEmail());
        }

        User user = new User()
                .setEmail(userDto.getEmail())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setPhone(userDto.getPhone())
                .setRoles(Set.of(Role.USER));

        userRepository.save(user);
        return user;
    }
}
