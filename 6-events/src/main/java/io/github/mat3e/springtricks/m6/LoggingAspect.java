package io.github.mat3e.springtricks.m6;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* io.github.mat3e.springtricks.m6.audit.AuditService.handle(..))")
    static void handling() {
    }

    @Before("handling()")
    void logBeforeHandling() {
        if (logger.isInfoEnabled()) {
            logger.info("Aspect before the actual event handling");
        }
    }
}
