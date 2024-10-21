package com.efremov.SpringBoot.RESTSecurityApp.security;


import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import com.efremov.SpringBoot.RESTSecurityApp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(name);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new UserDetailsSecurity(user.get());
    }
}
