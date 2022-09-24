package io.github.mat3e.springtricks.m6.config;

import io.github.mat3e.springtricks.m6.category.CategoryEvent;
import io.github.mat3e.springtricks.m6.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "6")
@RequiredArgsConstructor
class EventConfiguration {
    private final CountryService service;

    @Async
    @EventListener
    public void on(final CategoryEvent event) {
        service.handle(event);
    }
}

@EnableAsync
@Configuration
@Profile("!test")
@ConditionalOnProperty(name = "springtricks.module-number", havingValue = "6")
class AsyncConfiguration {
}
