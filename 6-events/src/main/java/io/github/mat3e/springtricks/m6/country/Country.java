package io.github.mat3e.springtricks.m6.country;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.*;

import static lombok.AccessLevel.PACKAGE;

@Table("countries")
@RequiredArgsConstructor(access = PACKAGE)
class Country {
    static Country newInstance(NewCountry source) {
        return new Country(
                source.code(),
                source.supportedLocales(),
                source.capitalCode()
        );
    }

    @Id
    @Column("id")
    private final String code;
    @Column("locales")
    private final Set<String> supportedLocales;
    @Column("capital")
    private final String capitalCityCode;

    boolean supports(Locale locale) {
        return supportedLocales.contains(locale.getLanguage());
    }

    CountryDto toDto() {
        return new CountryDto(
                code,
                new ArrayList<>(supportedLocales),
                capitalCityCode
        );
    }
}

record CountryDto(String code, List<String> supportedLocales, String capitalCode) implements SimpleCountryDto {
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

    @Override
    public String getCode() {
        return code();
    }

    @Override
    public List<String> getSupportedLocales() {
        return supportedLocales();
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
        return Country.newInstance(this);
    }
}

