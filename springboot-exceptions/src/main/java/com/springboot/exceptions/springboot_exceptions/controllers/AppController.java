package com.springboot.exceptions.springboot_exceptions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.springboot.exceptions.springboot_exceptions.exceptions.UserNotFoundException;
import com.springboot.exceptions.springboot_exceptions.models.domain.User;
import com.springboot.exceptions.springboot_exceptions.services.IUserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private IUserService userService;

    @Autowired
    @Qualifier("userList")
    private List<User> users;

    @GetMapping
    public String index() {
        // int value = 100 / 0; error ArithmeticException
        int value = Integer.parseInt("10x"); // error NumberFormatException
        System.out.println(value);
        return "OK 200";
    }

    @GetMapping("/list")
    public List<User> listaUsers() {
        return users;
    }

    @GetMapping("/list/{id}")
    public User list(@PathVariable(name = "id") Integer id) {
        User user = userService.findById(id) // si recibe null desde findById(), lanza Exception personalizada
                .orElseThrow(() -> new UserNotFoundException("Error, el usuario no existe"));
        return user;
    }

}
