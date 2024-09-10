package com.efremov.SpringBoot.RESTSecurityApp.controllers;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import com.efremov.SpringBoot.RESTSecurityApp.services.UserService;
import com.efremov.SpringBoot.RESTSecurityApp.util.UserErrorResponse;
import com.efremov.SpringBoot.RESTSecurityApp.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Can use Hibernate Validator for exception on input data and not null.
    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.createUser(user.getName(), user.getAge(), user.getCity());
        return ResponseEntity.ok().build();
    }

    //How does this work with RequestParam? Exception on Long type id.
    @PostMapping("/updateUser/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody User updateUser) {
        userService.updateUser(id, updateUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception) {
        UserErrorResponse response = new UserErrorResponse("User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
