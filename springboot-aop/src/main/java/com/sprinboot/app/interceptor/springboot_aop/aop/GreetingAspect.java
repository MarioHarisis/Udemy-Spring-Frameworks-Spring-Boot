package com.sprinboot.app.interceptor.springboot_aop.aop;

import org.slf4j.Logger;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/* Indica que esta clase es un Aspecto de AOP, es decir, 
contiene lógica que se ejecutará en ciertos puntos del código */
@Aspect
@Component
@Order(2)
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass()); // creación de logger

    // @Before(...) → Define que este Advice (consejo) se ejecutará antes del método
    // especificado.
    /*
     * execution(...) → Define un "punto de corte" (PointCut) que intercepta un
     * método específico.
     * 
     * com.sprinboot.app.interceptor.springboot_aop.services.GreetingService.
     * sayHello(..)
     * 
     * Intercepta el método sayHello(..) de la clase GreetingService.
     * Más adelante integramos "*" y nos permite hacer referencia a todos los método
     * sque se encuentren
     * en la GreetingService interface, podríamos hacer lo mismmo para la carpeta
     * superior(services)
     * y así en adelante
     * 
     * (..) → Indica que puede recibir cualquier número de argumentos de cualquier
     * tipo.
     */
    @Before("execution(* com.sprinboot.app.interceptor.springboot_aop.services.GreetingServicePointCut.*(..))") // PointCut
    public void loggerBefore(JoinPoint joinPoint) { // JoinPoint → Representa el punto de ejecución del método
                                                    // interceptado

        String method = joinPoint.getSignature().getName(); // Obtiene el nombre del método interceptado.
        String args = Arrays.toString(joinPoint.getArgs()); // Obtiene los argumentos que se pasaron al
                                                            // método que sea ("*")
        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    // se ejecuta después del método SIEMPRE
    @After("GreetingServicePointCut.greetingLoggerPointCut()") // PointCut
    public void loggerAfter(JoinPoint joinPoint) { // JoinPoint → Representa el punto de ejecución del método

        String method = joinPoint.getSignature().getName(); // Obtiene el nombre del método interceptado.
        String args = Arrays.toString(joinPoint.getArgs()); // Obtiene los argumentos que se pasaron al
        logger.info("Despues: " + method + " con los argumentos " + args);
    }

    // se ejecuta después de retornar método cuando NO ocurra ningún error
    @AfterReturning("GreetingServicePointCut.greetingLoggerPointCut()") // PointCut
    public void loggerAfterReturning(JoinPoint joinPoint) { // JoinPoint → Representa el punto de ejecución del método

        String method = joinPoint.getSignature().getName(); // Obtiene el nombre del método interceptado.
        String args = Arrays.toString(joinPoint.getArgs()); // Obtiene los argumentos que se pasaron al
        logger.info("Despues de retornar: " + method + " con los argumentos " + args);
    }

    // se ejecuta después del método si existe aluna excepción
    @AfterThrowing("GreetingServicePointCut.greetingLoggerPointCut()") // PointCut
    public void loggerAfterThrowing(JoinPoint joinPoint) { // JoinPoint → Representa el punto de ejecución del método

        String method = joinPoint.getSignature().getName(); // Obtiene el nombre del método interceptado.
        String args = Arrays.toString(joinPoint.getArgs()); // Obtiene los argumentos que se pasaron al
        logger.info("Despues de lanzar excepcion: " + method + " con los argumentos " + args);
    }

    // Advice que se ejecuta antes y después de la ejecución de un método
    @Around("GreetingServicePointCut.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable { // throws Throwable: Indica que puede
                                                                                 // lanzar cualquier excepción
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;
        try {
            logger.info("El método" + method + "() con los parametros" + args, args);
            result = joinPoint.proceed(); // joinPoint.proceed(): Ejecuta el método interceptado y guarda su resultado
            logger.info("El metodo " + method + "() retorna el resultado: " + result);
            return result;
        } catch (Throwable e) {
            logger.error("Error en la llamada del metodo " + method + "()");
            throw e; // por esto necesitamos Throwable
        }
    }
}
