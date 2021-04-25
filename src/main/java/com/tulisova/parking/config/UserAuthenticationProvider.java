package com.tulisova.parking.config;

import com.tulisova.parking.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {
        if (usernamePasswordAuthenticationToken.getCredentials() == null || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Username or password may not be null.");
        }
        if (!passwordEncoder.matches(
                (String) usernamePasswordAuthenticationToken.getCredentials(),
                userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }

    @Override
    protected UserDetails retrieveUser(
            String s,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {
        return userDetailsService.loadUserByUsername(s);
    }
}
