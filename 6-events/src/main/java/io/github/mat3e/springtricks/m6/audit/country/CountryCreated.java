package io.github.mat3e.springtricks.m6.audit.country;

import java.time.Instant;

public record CountryCreated(String code, Instant occurredOn) implements CountryEvent {
    public CountryCreated(String code) {
        this(code, Instant.now());
    }
}
