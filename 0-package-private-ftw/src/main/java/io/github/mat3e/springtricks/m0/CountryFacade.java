package io.github.mat3e.springtricks.m0;

import java.util.Locale;
import java.util.Optional;

public interface CountryFacade {
    Optional<CountryWithCapitalDto> findDetailedByCodeAndLocale(String countryCode, Locale locale);

    Optional<CountryDto> findByCode(String countryCode);
}
