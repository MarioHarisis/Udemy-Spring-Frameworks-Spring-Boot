package com.sprinboot.app.interceptor.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * ¿Para qué se usa esta clase?
 * Sirve como un repositorio de Pointcuts que pueden ser reutilizados en otros aspectos 
 * (@Before, @After, @Around, etc.).
 * 
 * Evita la duplicación de código en expresiones execution(...).
 * Permite interceptar métodos de GreetingService para aplicar lógica adicional 
 * (logs, métricas, seguridad, etc.).
 */
@Aspect
@Component
public class GreetingServicePointCut {

    // Define un conjunto de métodos que pueden ser interceptados.
    @Pointcut("execution(* com.sprinboot.app.interceptor.springboot_aop.services.GreetingService.*(..))")
    public void greetingLoggerPointCut() {
    }

    // Define un conjunto de métodos que pueden ser interceptados.
    @Pointcut("execution(* com.sprinboot.app.interceptor.springboot_aop.services.GreetingService.*(..))")
    public void greetingFooLoggerPointCut() {
    }
}
