package io.github.mat3e.springtricks.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(name = "springtricks.module-number")
public @interface ConditionalOnModuleNumber {
    @AliasFor(annotation = ConditionalOnProperty.class, attribute = "havingValue")
    String value() default "";
}
