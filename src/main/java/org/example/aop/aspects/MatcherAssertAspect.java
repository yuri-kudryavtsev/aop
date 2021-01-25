package org.example.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 24.01.2021
 *
 * @author Yuri Kudryavtsev
 * skype: yuri.kudryavtsev.indeed
 * email: ykudryavtsev@maxilect.com
 */

@Aspect
public class MatcherAssertAspect {
    private static final Logger log = LoggerFactory.getLogger(MatcherAssertAspect.class);

    @Pointcut("execution(* assertThat(..))")
    void inMethods() {
    }

    @Pointcut("execution(* org.hamcrest.MatcherAssert.*(..))")
    void inMatcherAssert() {
    }

    @Pointcut("execution(public * *(..))")
    void anyPublic() {
    }

    @Around("anyPublic() && inMatcherAssert() && inMethods()")
    public Object handleAssert(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            if (WithRetriesAspect.isProcessing()) {
                throw throwable;
            } else {
                log.warn(throwable.toString());
            }
        }
        return result;
    }
}
