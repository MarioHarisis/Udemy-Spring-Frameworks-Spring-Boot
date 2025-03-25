package com.sprinboot.app.interceptor.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1) // primero en entrar, último en salir
@Component
@Aspect
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("GreetingServicePointCut.greetingFooLoggerPointCut()") // PointCut
    public void loggerBefore(JoinPoint joinPoint) { // JoinPoint → Representa el punto de ejecución del método

        String method = joinPoint.getSignature().getName(); // Obtiene el nombre del método interceptado.
        String args = Arrays.toString(joinPoint.getArgs()); // Obtiene los argumentos que se pasaron al
        logger.info("Antes Foo: " + method + " invocado con los parametros " + args);
    }

    @After("GreetingServicePointCut.greetingFooLoggerPointCut()") // PointCut
    public void loggerAfter(JoinPoint joinPoint) { // JoinPoint → Representa el punto de ejecución del método

        String method = joinPoint.getSignature().getName(); // Obtiene el nombre del método interceptado.
        String args = Arrays.toString(joinPoint.getArgs()); // Obtiene los argumentos que se pasaron al
        logger.info("Despues Foo: " + method + " invocado con los parametros " + args);
    }
}
