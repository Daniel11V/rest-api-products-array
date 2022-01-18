package com.coderhouse.service.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AspectAfterExample {
    private static final Logger logger = LogManager.getLogger(AspectAfterExample.class);
    @Pointcut("execution(* com.coderhouse.service.controller.ProductController.getProductById(..))")
    void alTerminarBusquedas() {}

    @Pointcut("execution(* com.coderhouse.service.controller.*.*(..))")
    void alTerminarBusquedasAll() {}

//    @After("alTerminarBusquedasAll()")
//    void afterAdviceMethodAll() {
//        logger.info("After perfect All");
//    }

//    @After("alTerminarBusquedas()")
//    void afterAdviceMethod() {
//        logger.info("After perfect Id");
//    }


//    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
//    void controllerClassMethods() {}
//
//    @Around("controllerClassMethods()")
//    Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.nanoTime();
//        Object ret = joinPoint.proceed();
//        long end = System.nanoTime();
//
//        logger.info("Se ejecutó el metodo con una duración de {} ms", TimeUnit.NANOSECONDS.toMillis(end - start));
//        return ret;
//    }
}
