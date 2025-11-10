package org.walkgo.api.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("admin")) throw new UsernameNotFoundException("User not found");
        return User.builder()
                .username("admin")
                .password("{noop}adminpass")
                .authorities(List.of(() -> "ROLE_ADMIN"))
                .build();
    }
}