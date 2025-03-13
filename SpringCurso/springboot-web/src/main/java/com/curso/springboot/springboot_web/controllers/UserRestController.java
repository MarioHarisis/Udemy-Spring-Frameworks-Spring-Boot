package com.curso.springboot.springboot_web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.springboot_web.models.User;
import com.curso.springboot.springboot_web.models.dto.UserDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api") // ruta por defecto del controller
public class UserRestController {

    @GetMapping("/details")
    public UserDto details() {

        User user = new User("Mario", "Harisis", null);

        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("Hola mundo Spring Boot");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user = new User("User", "Uno", null);
        User user2 = new User("User", "Dos", null);
        User user3 = new User("User", "Tres", null);

        // Tranformar en lista
        List<User> users = Arrays.asList(user, user2, user3);

        /*
         * List<User> users = new ArrayList<>();
         * users.add(user);
         * users.add(user2);
         * users.add(user3);
         */

        return users;
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap() {

        User user = new User("Usuario", "Dos", null);
        Map<String, Object> body = new HashMap<>();

        body.put("title", "Hola Mundo desde Spring");
        body.put("user", user);

        return body;
    }

}
