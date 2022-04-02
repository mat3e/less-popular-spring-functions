package io.github.mat3e.springtricks.m6;

import io.github.mat3e.springtricks.m6.category.CategoryEvent;
import org.aspectj.lang.JoinPoint;
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

    @Pointcut("execution(* io.github.mat3e.springtricks.m6.country.CountryService.handle(..))")
    static void handling() {
    }

    @Before("handling()")
    void logBeforeHandling(JoinPoint jp) {
        if (logger.isInfoEnabled()) {
            var event = jp.getArgs()[0];
            if (event instanceof CategoryEvent categoryEvent) {
                switch (categoryEvent.operation()) {
                    case CREATED -> logger.info("Aspect before the actual event handling");
                    case DELETED -> logger.info(categoryEvent.toString());
                }
            }
        }
    }
}