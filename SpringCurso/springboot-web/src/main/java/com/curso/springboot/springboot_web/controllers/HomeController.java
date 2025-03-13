package com.curso.springboot.springboot_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({ "/", "", "/home" })
    public String home() {

        return "redirect:/details"; // redirige, carga una página nueva, cambia la url
        // return "forward:/details"; mantiene la url pero carga la otra vista
    }

}
