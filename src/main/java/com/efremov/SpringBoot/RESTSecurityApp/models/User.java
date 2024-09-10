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
    @Column(name="Id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private Byte age;
    @Column(name="city")
    private String city;
    @Column(name="password")
    private String password;

    public User(String name, Byte age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
}
