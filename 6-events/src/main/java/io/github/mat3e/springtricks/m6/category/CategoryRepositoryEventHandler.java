package io.github.mat3e.springtricks.m6.category;

import io.github.mat3e.springtricks.config.ConditionalOnModuleNumber;
import io.github.mat3e.springtricks.m6.audit.category.CategoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
@ConditionalOnModuleNumber("6")
class CategoryRepositoryEventHandler {
    private final ApplicationEventPublisher publisher;

    @HandleAfterCreate
    void handleCreate(Category category) {
        publisher.publishEvent(CategoryEvent.created(category.getCode()));
    }

    @HandleAfterDelete
    void handleDelete(Category category) {
        publisher.publishEvent(CategoryEvent.deleted(category.getCode()));
    }
}
