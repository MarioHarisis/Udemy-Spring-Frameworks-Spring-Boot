package com.springboot.calendar.interceptor.springboot_horario.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController // clase que maneja solicitudes HTTP y devuelve respuestas en formato JSON o XML
public class AppController {

    @GetMapping("/foo") // Define un endpoint HTTP GET en la ruta "/foo"
    public ResponseEntity<?> foo(HttpServletRequest request) { // ResponseEntity representar una respuesta HTTP completa

        Map<String, Object> data = new HashMap<>();
        data.put("Title", "Biuenvenidos al sistema de atencion al cliente");
        data.put("time", new Date());
        data.put("message", request.getAttribute("message")); // agregamos atributo del request al Map
        return ResponseEntity.ok(data); // Retorna una respuesta HTTP 200 OK
        // con el contenido del mapa data en formato JSON
    }
}
