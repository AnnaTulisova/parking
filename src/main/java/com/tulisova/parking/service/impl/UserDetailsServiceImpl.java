package com.tulisova.parking.service.impl;

import com.tulisova.parking.dao.model.*;
import com.tulisova.parking.dao.model.User;
import com.tulisova.parking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles.stream().map(it -> it.name()).collect(Collectors.toList())) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
