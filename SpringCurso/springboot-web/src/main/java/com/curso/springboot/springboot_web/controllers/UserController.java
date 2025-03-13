package com.curso.springboot.springboot_web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.curso.springboot.springboot_web.models.User;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {

        User user = new User("Mario", "Harisis", "correo@correo.com");

        model.addAttribute("title", "Hola Mundo desde Spring");
        model.addAttribute("user", user);

        return "details";
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("title", "Lista de Usuarios");
        return "list";
    }

    // Marca atributo global al controlador
    @ModelAttribute("users")
    public List<User> usersModel() {
        return Arrays.asList(new User("User", "de la Lista", "emailUser"));
    }

    /*
     * @GetMapping("/fool")
     * public String fool() {
     * 
     * logger.info("Entra");
     * 
     * return "fool";
     * }
     */
}
