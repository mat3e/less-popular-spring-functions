package io.github.mat3e.springtricks.m1;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

interface CountryRepository {
    Optional<Country> findByCode(String countryCode);
}

class InMemoryCountryRepository implements CountryRepository {
    private final Map<String, String> countryToCapital = Map.of(
            "pl", "WAW",
            "ua", "KIE"
    );
    private final Map<String, Set<Locale>> countryToLocales = Map.of(
            "pl", Set.of(Locale.forLanguageTag("pl"), Locale.ENGLISH, Locale.forLanguageTag("uk"), Locale.GERMAN),
            "ua", Set.of(Locale.forLanguageTag("pl"), Locale.ENGLISH, Locale.forLanguageTag("uk"))
    );

    @Override
    public Optional<Country> findByCode(String countryCode) {
        return Optional.ofNullable(countryToCapital.get(countryCode))
                .map(capitalCode -> new Country(
                        countryCode,
                        countryToLocales.get(countryCode),
                        capitalCode
                ));
    }
}
