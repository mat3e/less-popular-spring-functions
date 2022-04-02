package io.github.mat3e.springtricks.m4;

import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
class CountryService {
    private final CountryRepository repository;
    private final CitySlugGenerator slugGenerator;
    private final CountryQueryRepository queryRepository;

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

    Optional<SimpleCountryDto> findByCode(String countryCode) {
        return queryRepository.findDtoByCode(countryCode);
    }

    void save(NewCountry toSave) {
        repository.save(toSave.toCountry());
    }
}
