package com.curso.springboot.springboot_web.models;

import lombok.*;

@Getter
@Setter

public class User {

    private String name;
    private String lastname;
    private String email;

    public User(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
}
