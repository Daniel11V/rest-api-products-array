package com.coderhouse.service.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AspectClient {

    private static final Logger logger = LogManager.getLogger(AspectClient.class);

    @Pointcut("@annotation(com.coderhouse.service.annotations.ClientAnnotation)")
    void clientMethods() {}

    @After("clientMethods()")
    void afterAdviceMethod(JoinPoint jp) {
        logger.info("After method {}", jp.getSignature().getName());
    }


    @Pointcut("execution(* com.coderhouse.service.service.ClientServiceImpl.*(..)) && !execution(* com.coderhouse.service.service.ClientServiceImpl.delete(..))")
    void clientServiceMethods() {}

    @Around("clientServiceMethods()")
    Object aroundAdviceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object ret = joinPoint.proceed();
        long end = System.nanoTime();

        logger.info("Se ejecutó el metodo {}, con una duración de {} ms este", joinPoint.getSignature().getName(), TimeUnit.NANOSECONDS.toMillis(end - start));
        return ret;
    }

    @Pointcut("execution(* com.coderhouse.service.service.ClientServiceImpl.update(..))")
    void clientServiceUpdate() {}

    @AfterThrowing("clientServiceUpdate()")
    void afterAdviceUpdate() {
        logger.info("After Throwing error in method 'update()'");
    }

}
