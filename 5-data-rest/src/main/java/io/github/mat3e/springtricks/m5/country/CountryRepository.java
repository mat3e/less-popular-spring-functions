package io.github.mat3e.springtricks.m5.country;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

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
