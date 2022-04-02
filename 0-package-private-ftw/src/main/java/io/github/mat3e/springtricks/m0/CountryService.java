package io.github.mat3e.springtricks.m0;

import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
class CountryService {
    private final CountryRepository repository;
    private final CitySlugGenerator slugGenerator;

    Optional<CountryWithCapitalDto> findDetailedByCodeAndLocale(String countryCode, Locale locale) {
        return repository.findByCode(countryCode)
                .filter(country -> country.supports(locale))
                .map(Country::toDto)
                .flatMap(countryDto -> countryDto.toCountryWithCapital(
                        new CityDto(
                                countryDto.capitalCode(),
                                slugGenerator.generateSlug(countryDto.capitalCode(), locale)
                        )
                ));
    }

    Optional<CountryDto> findByCode(String countryCode) {
        return repository.findByCode(countryCode).map(Country::toDto);
    }
}
