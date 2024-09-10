package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;

public interface UserCRUD {
    void registerUser(String name, byte age, String city, String password);
    void updateUser(long id, User updateUser);
    void deleteUser(Long id);
    User getMe();
    void auth();

}
