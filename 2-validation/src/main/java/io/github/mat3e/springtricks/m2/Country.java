package io.github.mat3e.springtricks.m2;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
class Country {
    private final String code;
    private final Set<Locale> supportedLocales;
    private final String capitalCityCode;

    Country(NewCountry source) {
        this(
                source.code(),
                source.supportedLocales().stream().map(Locale::forLanguageTag).collect(toSet()),
                source.capitalCode()
        );
    }

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

record NewCountry(
        @Pattern(regexp = "[a-z]{2}") String code,
        @NotEmpty Set<@Pattern(regexp = "[a-z]{2}") String> supportedLocales,
        @Pattern(regexp = "[A-Z]{3}") String capitalCode
) {
    Country toCountry() {
        return new Country(this);
    }
}

