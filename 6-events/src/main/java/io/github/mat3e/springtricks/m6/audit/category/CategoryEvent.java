package io.github.mat3e.springtricks.m6.audit.category;

import io.github.mat3e.springtricks.m6.audit.Event;

import java.time.Instant;

public sealed interface CategoryEvent extends Event {

    static CategoryEvent created(String code) {
        return new CategoryCreated(code);
    }

    static CategoryEvent deleted(String code) {
        return new CategoryDeleted(code);
    }

    enum Operation {
        CREATED, DELETED
    }

    String code();

    Operation operation();
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
