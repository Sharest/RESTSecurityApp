package com.efremov.SpringBoot.RESTSecurityApp.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorPermission extends Throwable {
    private String message;

    public UserErrorPermission(String message) {
        this.message = message;
    }
}
