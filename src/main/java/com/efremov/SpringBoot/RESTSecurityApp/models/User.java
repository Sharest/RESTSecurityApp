package com.efremov.SpringBoot.RESTSecurityApp.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Byte age;

    private String city;

    private String password;


    public User(String name, Byte age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

}
