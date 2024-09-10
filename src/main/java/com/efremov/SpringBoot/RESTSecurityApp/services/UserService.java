package com.efremov.SpringBoot.RESTSecurityApp.services;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import com.efremov.SpringBoot.RESTSecurityApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserCRUD{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void registerUser(String name, byte age, String city, String password) {

    }

    @Override
    public void updateUser(long id, User updateUser) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getMe() {
        return null;
    }

    @Override
    public void auth() {

    }
}
