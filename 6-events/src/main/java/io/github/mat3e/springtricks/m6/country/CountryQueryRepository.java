package io.github.mat3e.springtricks.m6.country;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface CountryQueryRepository extends Repository<Country, String> {
    Optional<SimpleCountryDto> findDtoByCode(String code);
}

interface SimpleCountryDto {
    String getCode();

    List<String> getSupportedLocales();
}
