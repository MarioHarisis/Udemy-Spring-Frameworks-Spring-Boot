package com.springboot.exceptions.springboot_exceptions.models.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private String lastname;

    public User(Integer id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

}
