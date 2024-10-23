package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import com.efremov.SpringBoot.RESTSecurityApp.repositories.UserRepository;
import com.efremov.SpringBoot.RESTSecurityApp.security.JwtUtil;
import com.efremov.SpringBoot.RESTSecurityApp.security.MyUserDetailsService;
import com.efremov.SpringBoot.RESTSecurityApp.security.UserDetailsSecurity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserCRUD {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, MyUserDetailsService myUserDetailsService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getName());

        return token;
    }

    @Override
    public void updateUser(long id, User updateUser) {
        updateUser.setId(id);
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsSecurity userDetails = (UserDetailsSecurity) auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public String auth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsSecurity userDetails = (UserDetailsSecurity) auth.getPrincipal();

        return jwtUtil.generateToken(userDetails.getUsername());
    }

}
