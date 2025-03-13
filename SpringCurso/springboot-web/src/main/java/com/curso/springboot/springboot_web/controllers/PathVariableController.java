package com.curso.springboot.springboot_web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.springboot_web.models.dto.ParamDto;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    /*
     * Para poder capturar un path que pueda variar, metemos entre llaves la parte
     * variable
     * y anotamos con @PathVariable
     */
    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message) {
        ParamDto param = new ParamDto();
        param.setMessage(message);

        return param;
    }

    // PathVariable n param con Map
    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);

        return json;
    }

}
