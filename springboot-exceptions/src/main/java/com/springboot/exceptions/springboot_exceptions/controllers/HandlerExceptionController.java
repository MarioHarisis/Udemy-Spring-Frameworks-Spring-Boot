package com.springboot.exceptions.springboot_exceptions.controllers;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springboot.exceptions.springboot_exceptions.exceptions.UserNotFoundException;
import com.springboot.exceptions.springboot_exceptions.models.Error;

/* Esta anotación, es una combinación de @ControllerAdvice y @ResponseBody. Le indica a Spring 
 * que esta clase manejará excepciones en los controladores REST de manera global 
 *
 * @ControllerAdvice se utiliza para definir un controlador global que puede interceptar 
 * excepciones lanzadas por otros controladores.
 * @ResponseBody asegura que las respuestas que produce este controlador se devuelvan en 
 * formato JSON (o similar), que es adecuado para servicios REST.*/
@RestControllerAdvice
public class HandlerExceptionController {

    // Mapeado de la excepción, cuando haya una ArithmeticException entrará aquí
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Error> divisionByZero(Exception e) {
        /*
         * ResponseEntity es una clase de Spring utilizada para representar una
         * respuesta HTTP completa en una aplicación web. Esta clase permite definir no
         * solo el cuerpo del mensaje que se devolverá al cliente, sino también detalles
         * importantes como el código de estado HTTP, las cabeceras HTTP, y otras
         * configuraciones de la respuesta.
         */

        Error error = new Error();
        error.setDate(new Date());

        // HttpStatus es una enumeración (enum) en Spring que representa los diferentes
        // códigos de estado HTTP que se pueden usar para responder a solicitudes HTTP.

        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        // Simplemente dos formas de devolver la respuesta
        // return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException e) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    // Otra forma de responder a un error, con Map
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatEx(Exception e) {

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Numero incorrecto, no tiene formato de digito");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    // Manejar nuestra propia Exception
    @ExceptionHandler({ NullPointerException.class,
            HttpMessageNotWritableException.class,
            UserNotFoundException.class }) // Aquí se está manejando nuestra Exception
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception e) {

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Usuario no existe");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }
}
