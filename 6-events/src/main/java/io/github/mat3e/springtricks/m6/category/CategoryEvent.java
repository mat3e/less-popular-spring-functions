package io.github.mat3e.springtricks.m6.category;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;

public sealed interface CategoryEvent {
    enum Operation {
        CREATED, DELETED
    }

    String code();

    Operation operation();

    Instant occurredOn();
}

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
class CategoryEventHandler {
    private final ApplicationEventPublisher publisher;

    @HandleAfterCreate
    void handleCreate(Category category) {
        publisher.publishEvent(new CategoryCreated(category.getCode()));
    }

    @HandleAfterDelete
    void handleDelete(Category category) {
        publisher.publishEvent(new CategoryDeleted(category.getCode()));
    }
}

record CategoryCreated(String code, Instant occurredOn) implements CategoryEvent {
    CategoryCreated(String code) {
        this(code, Instant.now());
    }

    @Override
    public Operation operation() {
        return Operation.CREATED;
    }
}

record CategoryDeleted(String code, Instant occurredOn) implements CategoryEvent {
    CategoryDeleted(String code) {
        this(code, Instant.now());
    }

    @Override
    public Operation operation() {
        return Operation.DELETED;
    }
}
