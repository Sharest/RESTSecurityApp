package com.efremov.SpringBoot.RESTSecurityApp.repositories;

import com.efremov.SpringBoot.RESTSecurityApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
