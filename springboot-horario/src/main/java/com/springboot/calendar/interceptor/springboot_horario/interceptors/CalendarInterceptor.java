package com.springboot.calendar.interceptor.springboot_horario.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}") // inyectar valores desde el archivo de configuración
    private int open;

    @Value("${config.calendar.close}") // inyectar valores desde el archivo de configuración
    private int close;

    /*
     * Este es el método que se ejecuta antes de que el controlador maneje la
     * solicitud. Recibe tres parámetros:
     * 
     * HttpServletRequest request: El objeto de solicitud HTTP.
     * 
     * HttpServletResponse response: El objeto de respuesta HTTP.
     * 
     * Object handler: El manejador del controlador que procesará la solicitud (no
     * se usa en este caso).
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /*
         * Calendar es una clase abstracta. Es decir, no se puede instanciar
         * directamente usando el operador new (como se hace con otras clases). En lugar
         * de eso, debemos obtener una instancia de Calendar a través de su método
         * estático getInstance(), que devuelve una implementación específica de la
         * clase Calendar.
         */
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // Obtiene la hora del día actual (en formato de 24 horas)

        // comprobar si está en el rango de horas
        if (hour >= open && hour < close) {
            StringBuilder message = new StringBuilder("Bienvenidos al horario de atención al cliente");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append("hrs. ");
            message.append("hasta las ");
            message.append(close);
            message.append("hrs. ");
            message.append("Gracias por su visita. ");
            request.setAttribute("message", message.toString()); // atributo "message" de request
            return true; // permite que la solicitud continúe
        }
        // devolvemos mensaje de fuera de servicio

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();

        StringBuilder message = new StringBuilder("Cerrado, fuera de horario ");
        message.append("por favor visitenos desde las ");
        message.append(open);
        message.append(" hasta las ");
        message.append(close);
        data.put("message", message.toString());
        data.put("Date: ", new Date().toString());

        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(data));
        // Si la hora actual no está dentro del rango de atención (fuera del horario de
        // apertura y cierre), el interceptor devuelve false, lo que previene que la
        // solicitud llegue al controlador
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {

    }

}
