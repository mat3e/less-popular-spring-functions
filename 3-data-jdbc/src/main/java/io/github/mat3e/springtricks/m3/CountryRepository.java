package io.github.mat3e.springtricks.m3;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

interface CountryRepository {
    Optional<Country> findByCode(String countryCode);

    void save(Country toSave);
}

interface JdbcCountryRepository extends CountryRepository, Repository<Country, String> {
    @Override
    default void save(Country toSave) {
        var source = toSave.toDto();
        save(source.code(), String.join(",", source.supportedLocales()), source.capitalCode());
    }

    @Modifying
    @Query("merge into countries key (id) values (:id, :locales, :capital)")
    void save(String id, String locales, String capital);
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
