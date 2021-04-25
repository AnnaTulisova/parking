package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.Role;
import com.tulisova.parking.dao.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private Map<String, UserDetails> users;

    @PostConstruct
    public void init() {
        users = Map.of("fox", new User()
                .setId(0)
                .setEmail("fox").setPassword("$2a$10$xPHX13ereSkSkoCCm9XRB.y7vz6QjSs1jhLSQBYKuqK9tguqPWw0C")
                .setRoles(Set.of(Role.USER))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(users.get(email))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with email %s not founded", email)));
    }
}
