package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface UserCRUD {
    void createUser(String name, byte age, String city);
    void updateUser(long id, User updateUser);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();

}
