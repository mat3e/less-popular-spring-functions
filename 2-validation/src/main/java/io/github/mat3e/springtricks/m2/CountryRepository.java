package io.github.mat3e.springtricks.m2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

interface CountryRepository {
    Optional<Country> findByCode(String countryCode);

    void save(Country toSave);
}

class InMemoryCountryRepository implements CountryRepository {
    private final Map<String, Country> data = new HashMap<>(Map.of(
            "pl", new NewCountry("pl", Set.of("pl", "en", "uk", "de"), "WAW").toCountry(),
            "ua", new NewCountry("ua", Set.of("pl", "en", "uk"), "KIE").toCountry()
    ));

    @Override
    public Optional<Country> findByCode(String countryCode) {
        return Optional.ofNullable(data.get(countryCode));
    }

    @Override
    public void save(Country toSave) {
        data.put(toSave.toDto().code(), toSave);
    }
}
