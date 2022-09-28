package io.github.mat3e.springtricks.m6.country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CountryServiceTest {

    private static final CountryService toTest = new CountryConfiguration().countryService();

    private static final String SUPPORTED_COUNTRY = "ua";

    @ParameterizedTest
    @ValueSource(strings = {"ru", "de"})
    void findByCode_unsupportedCountry_returnsEmpty(String unsupportedCountry) {
        // when
        var result = toTest.findByCode(unsupportedCountry);

        then(result).isEmpty();
    }

    @Test
    void findByCode_supportedCountry_returnsCountryDto() {
        // when
        var result = toTest.findByCode(SUPPORTED_COUNTRY);

        then(result).isNotEmpty();
        then(result.get().getCode()).isEqualTo(SUPPORTED_COUNTRY);
        then(result.get().getSupportedLocales()).contains("pl", "en", "uk");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ru", "de"})
    void findDetailedByCodeAndLocale_unsupportedCountry_returnsEmpty(String unsupportedCountry) {
        // when
        var result = toTest.findDetailedByCodeAndLocale(unsupportedCountry, Locale.ENGLISH);

        then(result).isEmpty();
    }

    @Test
    void findDetailedByCodeAndLocale_unsupportedLocale_returnsEmpty() {
        // when
        var result = toTest.findDetailedByCodeAndLocale(SUPPORTED_COUNTRY, Locale.forLanguageTag("ru"));

        then(result).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void findDetailedByCodeAndLocale_supportedCountry_returnsEmpty(Locale locale, String expectedSlug) {
        // when
        var result = toTest.findDetailedByCodeAndLocale(SUPPORTED_COUNTRY, locale);

        then(result).isNotEmpty();
        then(result.get().country().code()).isEqualTo(SUPPORTED_COUNTRY);
        then(result.get().country().supportedLocales()).contains(locale.getLanguage());
        then(result.get().capital().code()).isEqualToIgnoringCase("KIE");
        then(result.get().capital().slug()).isEqualTo(expectedSlug);
    }

    private static Stream<Arguments> findDetailedByCodeAndLocale_supportedCountry_returnsEmpty() {
        return Stream.of(
                arguments(Locale.forLanguageTag("uk"), "kiyiv"),
                arguments(Locale.forLanguageTag("pl"), "kijow")
        );
    }

    @Test
    void save_addsNewCountry() {
        // given
        String romania = "ro";
        var toSave = new NewCountry(romania, Set.of("pl", "en", "ro", "de"), "BUC");

        // when
        toTest.save(toSave);
        // and
        Optional<CountryWithCapitalDto> newCityFound = toTest.findDetailedByCodeAndLocale(romania, Locale.ENGLISH);

        then(newCityFound).isNotEmpty();
        then(newCityFound.get().country().code()).isEqualTo(romania);
        then(newCityFound.get().country().capitalCode()).isEqualTo("BUC");
        then(newCityFound.get().country().supportedLocales()).contains("pl", "en", "ro", "de");
        then(newCityFound.get().capital().code()).isEqualTo("BUC");
        then(newCityFound.get().capital().slug()).isNull();
    }
}
