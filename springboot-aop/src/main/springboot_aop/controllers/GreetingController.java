package com.sprinboot.app.interceptor.springboot_aop.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.app.interceptor.springboot_aop.services.GreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/*
  ¿Qué hace @RestController?
 
    Indica que la clase es un controlador REST: Se usa para manejar solicitudes HTTP 
    (GET, POST, PUT, DELETE, etc.).

    Devuelve datos en formato JSON o XML: Gracias a @ResponseBody, no es necesario escribir 
    @ResponseBody en cada método.
    
    Se usa junto con @RequestMapping o @GetMapping, @PostMapping, etc.: 
    Permite mapear rutas HTTP a métodos específicos.
 */
@RestController
public class GreetingController {

  /*
   * Spring busca automáticamente un bean (una instancia administrada por Spring)
   * que coincida con el tipo de la variable y lo inyecta.
   */
  @Autowired
  private GreetingService greetingService;

  @GetMapping("/greeting")
  public ResponseEntity<?> greeting() {
    /*
     * - ResponseEntity es una clase de Spring que se usa para devolver respuestas
     * HTTP.
     * - .ok(...) indica que la respuesta tendrá un código de estado 200 (OK)
     * 
     */
    return ResponseEntity
        .ok(Collections.singletonMap(
            "greeting",
            greetingService.sayHello("Pepe", "Hola que tal")));
  }

  @GetMapping("/greeting-error")
  public ResponseEntity<?> greetingError() {
    return ResponseEntity
        .ok(Collections.singletonMap(
            "greeting",
            greetingService.sayHelloError("Pepe", "Hola que tal")));
  }
}
