package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import com.efremov.SpringBoot.RESTSecurityApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/updateUser/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody User updateUser) {
        userService.updateUser(id, updateUser);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
    @GetMapping("/getMe")
    public User getMe() {
        return userService.getMe();
    }
    @GetMapping("/auth")
    public String auth() {
       return userService.auth();
    }

}
