package io.github.mat3e.springtricks.m6.audit.country;

import io.github.mat3e.springtricks.m6.audit.Event;

public sealed interface CountryEvent extends Event permits CountryCreated {
    String code();
}

