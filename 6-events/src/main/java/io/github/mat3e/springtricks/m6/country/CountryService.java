package io.github.mat3e.springtricks.m6.country;

import io.github.mat3e.springtricks.m6.category.CategoryEvent;
import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
public class CountryService {
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

    public void handle(CategoryEvent event) {
        System.out.println(event.code() + " " + event.operation());
    }
}
