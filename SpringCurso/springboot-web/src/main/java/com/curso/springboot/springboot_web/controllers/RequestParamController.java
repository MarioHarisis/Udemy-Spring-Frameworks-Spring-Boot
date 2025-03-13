package com.curso.springboot.springboot_web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.springboot_web.models.dto.ParamDto;
import com.curso.utils.loggerUtils;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    private static Logger logger = loggerUtils.getLogger();

    @GetMapping("/foo")
    // espera recibir un param message desde /foo
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Sin mensaje") String message) {
        /*
         * required modifica si es obligatorio o no, en este caso si no hay mensaje
         * devolverá null
         * defaultValue: lo que aparece cuando no hay mensaje
         * name: se puede establecer un nombre, si no, será el que le asignemos por
         * param 'message'
         */

        logger.info("Este es el mensaje en logger {}", message);
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    @GetMapping("/bar")
    // Una anotación para cada param que queramos
    public ParamDto bar(@RequestParam String text, @RequestParam Integer code) {

        ParamDto params = new ParamDto();
        params.setMessage(text);
        params.setCode(code);

        return params;
    }

    // Recibir parametros con HttpServletRequest
    @GetMapping("/request")
    public ParamDto request(HttpServletRequest request) {
        ParamDto params = new ParamDto();

        try {
            // request devuelve un String, lo parseamos a Integer
            params.setCode(Integer.parseInt(request.getParameter("code")));
        } catch (NumberFormatException e) {
            // si se pasa un valor no numérico, se establecerá como 0
            params.setCode(0);
        }

        params.setMessage(request.getParameter("message"));
        return params;
    }

}
