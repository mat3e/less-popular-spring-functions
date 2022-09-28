package io.github.mat3e.springtricks.m6.config;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Profile("!test")
@ConditionalOnModuleNumber("6")
@Configuration(proxyBeanMethods = false)
class EventConfiguration {
}
