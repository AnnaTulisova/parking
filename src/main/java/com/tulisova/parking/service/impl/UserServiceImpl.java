package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.service.dto.*;
import com.tulisova.parking.service.exception.*;
import com.tulisova.parking.service.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private Map<String, User> users;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        users = new HashMap<>();
        users.put("fox", new User()
                .setId(0)
                .setEmail("fox")
                .setPassword("$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C")
                .setRoles(Set.of(Role.USER)));
    }

    @Override
    public User findUserByEmail(String email) {
        return Optional.ofNullable(users.get(email))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with email %s not founded", email)));
    }

    @Override
    public User registerNewUser(UserDto userDto) {

        if(users.containsKey(userDto.getEmail())) {
            throw new UserAlreadyExistException(userDto.getEmail());
        }

        User user = new User()
                .setEmail(userDto.getEmail())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setPhone(userDto.getPhone())
                .setRoles(Set.of(Role.USER));

        users.put(userDto.getEmail(), user);

        return user;
    }
}
