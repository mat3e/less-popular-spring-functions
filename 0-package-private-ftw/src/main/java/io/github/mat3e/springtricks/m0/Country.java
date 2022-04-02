package io.github.mat3e.springtricks.m0;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
class Country {
    private final String code;
    private final Set<Locale> supportedLocales;
    private final String capitalCityCode;

    boolean supports(Locale locale) {
        return supportedLocales.contains(locale);
    }

    CountryDto toDto() {
        return new CountryDto(
                code,
                supportedLocales.stream().map(Locale::getLanguage).toList(),
                capitalCityCode
        );
    }
}

record CountryDto(String code, List<String> supportedLocales, String capitalCode) {
    CountryDto(String code, List<String> supportedLocales, String capitalCode) {
        this.code = code;
        this.supportedLocales = List.copyOf(supportedLocales);
        this.capitalCode = capitalCode;
    }

    Optional<CountryWithCapitalDto> toCountryWithCapital(CityDto cityDto) {
        if (!capitalCode.equals(cityDto.code())) {
            return Optional.empty();
        }
        return Optional.of(new CountryWithCapitalDto(this, cityDto));
    }
}

record CountryWithCapitalDto(@JsonUnwrapped CountryDto country, CityDto capital) {
}

record CityDto(String code, String slug) {
}
