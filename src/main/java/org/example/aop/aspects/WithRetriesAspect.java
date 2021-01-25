package org.example.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.aop.annotations.WithRetries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created on 24.01.2021
 *
 * @author Yuri Kudryavtsev
 * skype: yuri.kudryavtsev.indeed
 * email: ykudryavtsev@maxilect.com
 */

@Aspect
public class WithRetriesAspect {
    private static final ThreadLocal<Boolean> processingWrapper = ThreadLocal.withInitial(() -> false);
    private static final Logger log = LoggerFactory.getLogger(WithRetriesAspect.class);

    public static Boolean isProcessing() {
        return processingWrapper.get();
    }

    @Around("@annotation(org.example.aop.annotations.WithRetries) && execution(* *(..))")
    public Object handleRetries(final ProceedingJoinPoint joinPoint) throws Throwable {
        processingWrapper.set(true);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        WithRetries annotation = method.getAnnotation(WithRetries.class);
        int retryCount = annotation.value();
        Throwable storedException = null;
        Object result = null;
        boolean processed = false;
        int i = 0;
        while (!processed && i < retryCount) {
            try {
                result = joinPoint.proceed();
                processed = true;
            } catch(Throwable throwable) {
                log.warn("Попытка №" +i+":\r\n"+throwable.toString());
                storedException = throwable;
            }
            i++;
        }

        processingWrapper.set(false);

        if(!processed) {
            assert storedException != null;
            throw storedException;
        }

        return result;
    }
}
