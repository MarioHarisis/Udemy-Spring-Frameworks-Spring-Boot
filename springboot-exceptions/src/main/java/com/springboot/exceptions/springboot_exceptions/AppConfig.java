package com.springboot.exceptions.springboot_exceptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.exceptions.springboot_exceptions.models.domain.User;

import java.util.List;
import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean("userList")
    List<User> userList() {

        List<User> users = Arrays.asList(new User(1, "User1", "ApellidoUser1"),
                new User(2, "User2", "ApellidoUser2"),
                new User(3, "User3", "ApellidoUser3"),
                new User(4, "User4", "ApellidoUser4"),
                new User(5, "User5", "ApellidoUser5"));

        return users;
    }

}
