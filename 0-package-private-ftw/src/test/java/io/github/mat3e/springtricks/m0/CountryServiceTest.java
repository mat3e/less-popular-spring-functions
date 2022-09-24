package io.github.mat3e.springtricks.m0;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CountryServiceTest {

    private static final CountryFacade toTest = new CountryConfiguration().countryFacade();

    private static final String SUPPORTED_COUNTRY = "ua";

    @ParameterizedTest
    @ValueSource(strings = {"ru", "de"})
    void findByCode_unsupportedCountry_returnsEmpty(String unsupportedCountry) {
        // when
        var result = toTest.findByCode(unsupportedCountry);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void findByCode_supportedCountry_returnsCountryDto() {
        // when
        var result = toTest.findByCode(SUPPORTED_COUNTRY);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().code()).isEqualTo(SUPPORTED_COUNTRY);
        assertThat(result.get().capitalCode()).isEqualTo("KIE");
        assertThat(result.get().supportedLocales()).contains("pl", "en", "uk");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ru", "de"})
    void findDetailedByCodeAndLocale_unsupportedCountry_returnsEmpty(String unsupportedCountry) {
        // when
        var result = toTest.findDetailedByCodeAndLocale(unsupportedCountry, Locale.ENGLISH);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void findDetailedByCodeAndLocale_unsupportedLocale_returnsEmpty() {
        // when
        var result = toTest.findDetailedByCodeAndLocale(SUPPORTED_COUNTRY, Locale.forLanguageTag("ru"));

        // then
        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void findDetailedByCodeAndLocale_supportedCountry_returnsEmpty(Locale locale, String expectedSlug) {
        // when
        var result = toTest.findDetailedByCodeAndLocale(SUPPORTED_COUNTRY, locale);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get().country().code()).isEqualTo(SUPPORTED_COUNTRY);
        assertThat(result.get().country().supportedLocales()).contains(locale.getLanguage());
        assertThat(result.get().capital().code()).isEqualToIgnoringCase("KIE");
        assertThat(result.get().capital().slug()).isEqualTo(expectedSlug);
    }

    private static Stream<Arguments> findDetailedByCodeAndLocale_supportedCountry_returnsEmpty() {
        return Stream.of(
                arguments(Locale.forLanguageTag("uk"), "kiyiv"),
                arguments(Locale.forLanguageTag("pl"), "kijow")
        );
    }
}
