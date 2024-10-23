package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserCRUD {
    String registerUser(User user);
    void updateUser(long id, User updateUser);
    void deleteUser(Long id);
    User getMe();
    String auth();

}
