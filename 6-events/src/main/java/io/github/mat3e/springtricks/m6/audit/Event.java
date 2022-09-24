package io.github.mat3e.springtricks.m6.audit;

import java.time.Instant;

public interface Event {
    Instant occurredOn();
}
