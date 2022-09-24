package io.github.mat3e.springtricks.m6.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@Profile("!test")
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "6")
class EventConfiguration {
}
