package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import com.efremov.SpringBoot.RESTSecurityApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    public void registerUser(String name, byte age, String city, String password) {

    }

    public void updateUser(long id, User updateUser) {

    }

    public void deleteUser(Long id) {

    }

    public User getMe() {
        return null;
    }

    public void auth() {

    }
}
