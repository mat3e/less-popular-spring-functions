package io.github.mat3e.springtricks.m5.country;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

class CitySlugGenerator {
    private final Map<String, Map<Locale, String>> codeToLocaleToSlug = Map.of(
            "WAW", Map.of(
                    Locale.forLanguageTag("pl"), "warszawa",
                    Locale.ENGLISH, "warsaw",
                    Locale.forLanguageTag("uk"), "varshava",
                    Locale.GERMAN, "warschau"
            ),
            "KIE", Map.of(
                    Locale.forLanguageTag("pl"), "kijow",
                    Locale.ENGLISH, "kyiv",
                    Locale.forLanguageTag("uk"), "kiyiv"
            )
    );

    String generateSlug(String cityCode, Locale inLocale) {
        return Optional.ofNullable(codeToLocaleToSlug.get(cityCode))
                .map(localeToSlug -> localeToSlug.get(inLocale))
                .orElse(null);
    }
}
