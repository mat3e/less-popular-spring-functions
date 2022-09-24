package io.github.mat3e.springtricks.m6.audit;

import io.github.mat3e.springtricks.m6.audit.category.CategoryEvent;
import io.github.mat3e.springtricks.m6.audit.country.CountryEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditService {
    @Async
    @EventListener
    public void handle(CategoryEvent event) {
        System.out.println("Category " + event.code() + " (" + event.operation() + ")");
    }

    @Async
    @EventListener
    public void handle(CountryEvent event) {
        System.out.println("Country " + event.code());
    }
}
